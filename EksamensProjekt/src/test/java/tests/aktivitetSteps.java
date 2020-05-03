package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Domaeneklasser.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Redskaber.Managers;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public class aktivitetSteps {
	Medarbejder leder;
	Medarbejder medarbejder;
	Projekt projekt;
	Aktivitet aktivitet;
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	IBrugttidManager brugttidManager= Managers.FaaBrugttidManager();
	
	@Given("der er et projekt {string} med lederen {string}")
	public void derErEtProjektMedLederen(String projektNavn, String lederID) {
		this.leder = new Medarbejder(lederID);
		this.projekt = new Projekt(projektNavn);
		this.projekt.Gem();
		//¯\_(ツ)_/¯
	}

	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String aktivitetID) {
		this.aktivitet= new Aktivitet(aktivitetID);
		assertTrue(this.projekt.tilfoejAktivitet(aktivitet));
	}
	
	@Then("projektet {string} har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String projektNavn, String aktivitetID) {
		assertTrue(aktivitetManager.eksisterer(this.aktivitet));
		assertTrue(this.aktivitet.getProjekt() == this.projekt);
	}
	
	
	// Forsoeger at tilfoeje aktivitet som allerede findes i projektet
	@Given("Projektet {string} har aktiviteten {string}")
	public void projektetHarAktiviteten(String projektNavn, String aktivitetID) {
	    this.projekt = new Projekt(projektNavn);
	    this.projekt.Gem();										//opret projekt
	    this.aktivitet = new Aktivitet(aktivitetID);				//opret aktivitet
	    this.projekt.tilfoejAktivitet(this.aktivitet);				//tilfoej aktivitet til projekt
		assertTrue(aktivitetManager.eksisterer(this.aktivitet));	//tjek at aktiviteten findes
		assertTrue(this.aktivitet.getProjekt() == this.projekt);		//tjek at aktivitet er i projekt
	}

	@When("lederen proever at lave en ny aktivitet med navnet {string}")
	public void lederenProeverAtLaveEnNyAktivitetMedNavnet(String string) {
		Aktivitet sammeNavn= new Aktivitet(this.aktivitet.getNavn());
		assertFalse(this.projekt.tilfoejAktivitet(sammeNavn));
	}

	@Then("Aktiviteten er ikke tilfoejet")
	public void aktivitetenErIkkeTilfoejet() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
// 	Medarbejder tilfoejer tid til en aktivitet.
	@Given("der er en medarbejder med ID'et {string} og et projekt med navnet {string}")
	public void derErEnMedarbejderMedIDEtOgEtProjektMedNavnet(String MedarbejderID, String ProjektNavn) {
	    this.medarbejder = new Medarbejder (MedarbejderID);
	    this.medarbejder.Gem();
	    this.projekt = new Projekt (ProjektNavn);
	    this.projekt.Gem();
	}

	@Given("Projektet {string} har en aktivitet {string}")
	public void projektetHarEnAktivitet(String ProjektNavn, String AktivitetID) {
	    this.aktivitet = new Aktivitet (AktivitetID);
	    assertTrue(this.projekt.tilfoejAktivitet(this.aktivitet));
	}
	
	@Given("Medarbejderen {string} arbejder paa aktiviteten {string}")
	public void medarbejderenArbejderPaaAktiviteten(String MedarbejderID, String AktivitetID) {
	    this.aktivitet.SaetMedarbejder(this.medarbejder);
	    assertTrue((this.aktivitet.Medarbejder()==this.medarbejder));
	    
	} 
	
	@Given("Aktiviteten {string} har mere end {int} ubrugte budgetterede timer")
	public void aktivitetenHarMereEndUbrugteBudgetteredeTimer(String AktivitetID, Integer BudgetteretTid) {
	    this.aktivitet.SaetBudgetteretTid(BudgetteretTid+1);
	}
	
	@When("Medarbejderen {string} tilfoejer {int} timer til aktiviteten {string}")
	public void medarbejderenTilfoejerTimerTilAktiviteten(String MedarbejderID, Integer int1, String AktivitetID) {
		Brugttid brugttid = new Brugttid(this.aktivitet, this.medarbejder, int1);
		assertTrue(this.aktivitet.TilfoejTid(brugttid));
	}
	
	@Then("Medarbejderens timer {int} er blevet noteret")
	public void medarbejderensTimerErBlevetNoteret(Integer int1) {
		
		//Kan vi se at timerne er registreret paa aktiviteten?
		boolean passThisTest = false;
		for(Entry<UUID, Brugttid> e : this.brugttidManager.AlleBrugttidEfterAktivitetOgMedarbejder(this.aktivitet, this.medarbejder)) {
			if (e.getValue().Aktivitet() == this.aktivitet && e.getValue().Medarbejder()==this.medarbejder && e.getValue().Tid()==int1) {
				passThisTest = true;
				break;
			}
		}
		assertTrue(passThisTest);
		
		//Kan medarbejderens se paa sit daglige timebrug, at han/hun har registreret timer i dag?
		passThisTest=false;
		for(Entry<UUID, Brugttid> e : this.brugttidManager.AlleBrugttidEfterMedarbejder(this.medarbejder)) {
			if (e.getValue().Dato().getDay()==(new Date()).getDay() && e.getValue().Medarbejder() == this.medarbejder) {
				passThisTest=true;
				break;
			}
		}
		assertTrue(passThisTest);
	}
	
	@When("aktivitetens navn aendres til {string}")
	public void aktivitetensNavnAendresTil(String nytNavn) {
	    this.aktivitet.setNavn(nytNavn);
	}
	
	@Then("aktivitetens navn er nu {string}")
	public void aktivitetensNavnErNu(String nytNavn) {
	    assertTrue(this.aktivitet.getNavn().equals(nytNavn));
	}

	@When("aktivitetens budgetterede tid aendres til {int}")
	public void aktivitetensBudgetteredeTidAendresTil(Integer int1) {
	    this.aktivitet.SaetBudgetteretTid(int1);
	}

	@Then("aktivitetens budgetterede tid er nu {int}")
	public void aktivitetensBudgetteredeTidErNu(Integer int1) {
	    assertTrue(this.aktivitet.getBudgetTid()==int1);
	}

	@When("aktivitetens start- og slut-tidspunkter aendres til hhv. uge {int} og {int}")
	public void aktivitetensStartOgSlutTidspunkterAendresTilHhvUgeOg(Integer int1, Integer int2) {
	    this.aktivitet.setStartUge(int1);
	    this.aktivitet.setSlutUge(int2);
	}

	@Then("aktivitetens start- og slut-tidspunkter er nu hhv. {int} og {int}")
	public void aktivitetensStartOgSlutTidspunkterErNuHhvOg(Integer int1, Integer int2) {
		assertTrue(this.aktivitet.getStartUge()==int1);
		assertTrue(this.aktivitet.getSlutUge() ==int2);
	}


}
