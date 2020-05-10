package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Domaeneklasser.*;
import Applikationslag.Infrastruktur.ServiceImplementationer.MedarbejderManager;
import Applikationslag.Infrastruktur.ServiceImplementationer.ProjektManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IBrugttidManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IProjektManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public class aktivitetSteps {
	Aktivitet aktivitet;
	Projekt Currentprojekt;Aktivitet Currentaktivitet;Medarbejder Currentmedarbejder;
	Brugttid Currentbrugttid;
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IBrugttidManager brugttidManager= Managers.FaaBrugttidManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	
	
	@Given("der er et projekt {string} med lederen {string}")
	public void derErEtProjektMedLederen(String projektNavn, String lederID) {
		Currentmedarbejder = new Medarbejder(lederID);
		Currentmedarbejder.Gem();
		Currentprojekt = new Projekt(projektNavn);
		assertTrue(Currentprojekt.Gem());
		//¯\_(ツ)_/¯
	}

	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String aktivitetID) {
		Currentaktivitet= new Aktivitet(aktivitetID);
		assertTrue(Currentprojekt.tilfoejAktivitet(Currentaktivitet));
	}
	
	@Then("projektet {string} har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String projektNavn, String aktivitetID) {
		assertTrue(aktivitetManager.eksisterer(Currentaktivitet));
		assertTrue(Currentaktivitet.getProjekt() == Currentprojekt);
	}
	
	
	// Forsoeger at tilfoeje aktivitet som allerede findes i projektet
	@Given("Projektet {string} har aktiviteten {string}")
	public void projektetHarAktiviteten(String projektNavn, String aktivitetID) {									//opret projekt
		Currentaktivitet = new Aktivitet(aktivitetID);				//opret aktivitet
	    assertTrue(Currentprojekt.tilfoejAktivitet(Currentaktivitet));				//tilfoej aktivitet til projekt
		assertTrue(aktivitetManager.eksisterer(Currentaktivitet));	//tjek at aktiviteten findes
		assertTrue(Currentaktivitet.getProjekt() == Currentprojekt);		//tjek at aktivitet er i projekt
	}

	@When("lederen proever at lave en ny aktivitet med navnet {string}")
	public void lederenProeverAtLaveEnNyAktivitetMedNavnet(String string) {
		Aktivitet sammeNavn= new Aktivitet(Currentaktivitet.getNavn());
		assertFalse(Currentprojekt.tilfoejAktivitet(sammeNavn));
	}

	@Then("Aktiviteten er ikke tilfoejet")
	public void aktivitetenErIkkeTilfoejet() {
	    //indeholder projektet aktiviteten nu 2 aktiviteter?
		assertTrue(aktivitetManager.AlleAktiviteterEfterProjekt(Currentprojekt).size()==1);
	    
	}
	
// 	Medarbejder tilfoejer tid til en aktivitet.
	@Given("der er en medarbejder med ID'et {string} og et projekt med navnet {string}")
	public void derErEnMedarbejderMedIDEtOgEtProjektMedNavnet(String MedarbejderID, String ProjektNavn) {
		Currentmedarbejder = new Medarbejder (MedarbejderID);
	    assertTrue(Currentmedarbejder.Gem());
	    Currentprojekt = new Projekt (ProjektNavn);
	    assertTrue(Currentprojekt.Gem());
	}

	@Given("Projektet {string} har en aktivitet {string}")
	public void projektetHarEnAktivitet(String ProjektNavn, String AktivitetID) {
		Currentaktivitet = new Aktivitet (AktivitetID);
	    assertTrue(Currentprojekt.tilfoejAktivitet(Currentaktivitet));
	}
	
	@Given("Medarbejderen {string} arbejder paa aktiviteten {string}")
	public void medarbejderenArbejderPaaAktiviteten(String MedarbejderID, String AktivitetID) {
	    assertTrue(Currentaktivitet.SaetMedarbejder(Currentmedarbejder));
	    assertTrue((Currentaktivitet.Medarbejder()==Currentmedarbejder));
	    
	} 
	
	@Given("Aktiviteten {string} har mere end {int} ubrugte budgetterede timer")
	public void aktivitetenHarMereEndUbrugteBudgetteredeTimer(String AktivitetID, Integer BudgetteretTid) {
		Currentaktivitet.SaetBudgetteretTid(BudgetteretTid+1);
	}
	
	@When("Medarbejderen {string} tilfoejer {int} timer til aktiviteten {string}")
	public void medarbejderenTilfoejerTimerTilAktiviteten(String MedarbejderID, Integer int1, String AktivitetID) {
		Brugttid brugttid = new Brugttid(Currentaktivitet, Currentmedarbejder, int1);
		assertTrue(Currentaktivitet.TilfoejTid(brugttid));
		assertTrue(brugttidManager.Eksisterer(brugttid));
	}
	
	@Then("Medarbejderens timer {int} er blevet noteret")
	public void medarbejderensTimerErBlevetNoteret(Integer int1) {
		
		//Kan vi se at timerne er registreret paa aktiviteten?
		boolean passThisTest = false;
		for(Entry<UUID, Brugttid> e : brugttidManager.AlleBrugttidEfterAktivitetOgMedarbejder(Currentaktivitet, Currentmedarbejder)) {
			if (e.getValue().Aktivitet() == Currentaktivitet && e.getValue().Medarbejder()==Currentmedarbejder && e.getValue().Tid()==int1) {
				passThisTest = true;
				break;
			}
		}
		assertTrue(passThisTest);
		
		//Kan medarbejderens se paa sit daglige timebrug, at han/hun har registreret timer i dag?
		passThisTest=false;
		for(Entry<UUID, Brugttid> e : brugttidManager.AlleBrugttidEfterMedarbejder(Currentmedarbejder)) {
			if (e.getValue().Dato().getDay()==(new Date()).getDay() && e.getValue().Medarbejder() == Currentmedarbejder) {
				passThisTest=true;
				break;
			}
		}
		assertTrue(passThisTest);
	}
	
	@When("aktivitetens navn aendres til {string}")
	public void aktivitetensNavnAendresTil(String nytNavn) {
		Currentaktivitet.setNavn(nytNavn);
	}
	
	@Then("aktivitetens navn er nu {string}")
	public void aktivitetensNavnErNu(String nytNavn) {
	    assertTrue(Currentaktivitet.getNavn().equals(nytNavn));
	}

	@When("aktivitetens budgetterede tid aendres til {int}")
	public void aktivitetensBudgetteredeTidAendresTil(Integer int1) {
		Currentaktivitet.SaetBudgetteretTid(int1);
	}

	@Then("aktivitetens budgetterede tid er nu {int}")
	public void aktivitetensBudgetteredeTidErNu(Integer int1) {
	    assertTrue(Currentaktivitet.getBudgetTid()==int1);
	}

	@When("aktivitetens start- og slut-tidspunkter aendres til hhv. uge {int} og {int}")
	public void aktivitetensStartOgSlutTidspunkterAendresTilHhvUgeOg(Integer int1, Integer int2) {
		Currentaktivitet.setStartUge(int1);
		Currentaktivitet.setSlutUge(int2);
	}

	@Then("aktivitetens start- og slut-tidspunkter er nu hhv. {int} og {int}")
	public void aktivitetensStartOgSlutTidspunkterErNuHhvOg(Integer int1, Integer int2) {
		assertTrue(Currentaktivitet.getStartUge()==int1);
		assertTrue(Currentaktivitet.getSlutUge() ==int2);
	}
	
	
	//Feature: ret i tid registreret.
	@Given("Vi kigger paa projektet {string}")
	public void viKiggerPaaProjektet(String projektnavn) {
	    Currentprojekt=projektManager.projektUdFraNavn(projektnavn);
	    assertTrue(Currentprojekt.getNavn().equals(projektnavn));
	}

	@Given("{string} har registreret {int} timer paa aktiviteten {string}")
	public void harRegistreretTimerPaaAktiviteten(String medarbejdernavn, Integer antalTimer, String aktivitetnavn) {
		Currentmedarbejder = medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn);
		Currentaktivitet = aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetnavn);
		assertTrue(Currentmedarbejder.getNavn().equals(medarbejdernavn));
		Date dato = new Date(2020, 10, 2);
		Currentbrugttid = new Brugttid(Currentaktivitet, Currentmedarbejder, antalTimer, dato);
		assertTrue(Currentbrugttid.getFlotTid().equals("5:00"));
		assertTrue(Currentaktivitet.TilfoejTid(Currentbrugttid));
	}

	@When("{string} retter tiden som han har brugt i aktiviteten til {int}")
	public void retterTidenSomHanHarBrugtIAktivitetenTil(String medarbejdernavn, Integer antalTimer) {
	    Currentbrugttid.AendreTid(antalTimer);
	}

	@Then("{string} har kun {int} timer registreret i aktiviteten {string}")
	public void harKunTimerRegistreretIAktiviteten(String medarbejdernavn, Integer antalTimer, String aktivitetnavn) {
	    List<Entry<UUID, Brugttid>> a = brugttidManager.AlleBrugttidEfterAktivitetOgMedarbejder(Currentaktivitet, Currentmedarbejder);
	    int b = 0;
	    for (int i = 0; i < a.size(); i++) {
	    	b=b+(a.get(i).getValue().Tid());
	    }
	    assertTrue(b==antalTimer);
	    
	}
}
