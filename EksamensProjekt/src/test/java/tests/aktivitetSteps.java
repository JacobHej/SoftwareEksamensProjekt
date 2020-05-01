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
		assertFalse(this.projekt.tilfoejAktivitet(this.aktivitet));
	}

	@Then("Kast exception {string}")
	public void kastException(String ErrorMessage) {
//	    throw new IllegalArgumentException(ErrorMessage);
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
	    this.aktivitet.SaetBudgetteretTid(BudgetteretTid);
	}
	
	@When("Medarbejderen {string} tilfoejer {int} timer til aktiviteten {string}")
	public void medarbejderenTilfoejerTimerTilAktiviteten(String MedarbejderID, Integer int1, String AktivitetID) {
		System.out.println(this.aktivitet.TilfoejTid(int1));
		assertTrue(this.aktivitet.TilfoejTid(int1, this.medarbejder));
	}

//	@Then("Medarbejderens timer er blevet noteret")
//	public void medarbejderensTimerErBlevetNoteret() {
//	    System.out.println(this.brugttidManager.AlleBrugttidEfterAktivitetOgMedarbejder(this.aktivitet, this.medarbejder));
//	    throw new io.cucumber.java.PendingException();
//	}
//	
}
