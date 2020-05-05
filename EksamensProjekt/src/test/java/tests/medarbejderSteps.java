package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Data.Datavedholdelsesklasser.ProjektData;
import Applikationslag.Domaeneklasser.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.IProjektManager;
import Applikationslag.Infrastruktur.ServiceImplementationer.ProjektManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Redskaber.Managers;

import java.util.Date;
import java.util.UUID;
import java.util.Map.Entry;

public class medarbejderSteps {
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	Medarbejder Currentmedarbejder;
	Aktivitet Currentaktivitet;
	Projekt Currentprojekt;
	
	
	@Given("medarbejder {string} har mindre end {int} vagter i uge {int}")
	public void medarbejderHarMindreEndVagterIUge(String Medarbejdernavn, Integer int1, Integer int2) {
	    Currentmedarbejder = new Medarbejder(Medarbejdernavn);
	    Currentmedarbejder.Gem();
	    assertTrue(medarbejderManager.AktiviteterIDenneUge(int2, 2020, Currentmedarbejder)<20);
	}

	@When("medarbejder {string} faar aktiviteten {string}")
	public void medarbejderFaarAktiviteten(String Medarbejdernavn, String aktivitetnavn) {
	    Currentaktivitet=new Aktivitet (aktivitetnavn);
	    Currentprojekt=new Projekt ("DetteErEtNyProjekt");
	    Currentprojekt.Gem();
	    Currentprojekt.tilfoejAktivitet(Currentaktivitet);
	    Currentaktivitet.SaetMedarbejder(Currentmedarbejder);
	}

	@Then("medarbejder {string} har faaet aktiviteten {string}")
	public void medarbejderHarFaaetAktiviteten(String string, String string2) {
	    assertTrue(Currentaktivitet.Medarbejder()==Currentmedarbejder);
	}
	
	@Given("medarbjeder {string} som har {int} aktiviteter i uge {int}")
	public void medarbjederSomHarAktiviteterIUge(String medarbejederNavn, Integer Aktiviteter, Integer Uge2) {
		Currentmedarbejder = new Medarbejder(medarbejederNavn);
	    Currentmedarbejder.Gem();
	    Projekt Fyld = new Projekt("Fillerprojekt for" + Currentmedarbejder.getNavn());
	    Fyld.Gem();
	    
	    for (int i = 0; i < Aktiviteter; i++){
	    	Aktivitet Fill = new Aktivitet("FyldeAktivitet"  + i);
	    	Fyld.tilfoejAktivitet(Fill);
	    	Fill.setStartaar(2020);Fill.setSlutaar(2020);
	    	Fill.setStartUge(Uge2);Fill.setSlutUge(Uge2);
	    	Fill.SaetMedarbejder(Currentmedarbejder);
	    }
	    assertTrue((medarbejderManager.AktiviteterIDenneUge(Uge2, 2020, Currentmedarbejder))==Aktiviteter);
	    assertTrue((medarbejderManager.AktiviteterIDenneUge(Uge2, 2020, Currentmedarbejder))==20);
	}
	
	
	
	@When("{string} faar endnu en aktivitet i uge {int}")
	public void faarEndnuEnAktivitetIUge(String medarbejdernavn, Integer ugenr) {
	    Currentmedarbejder = medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn);
	    Currentprojekt=new Projekt("TestProjekt");Currentprojekt.Gem();
	    Currentaktivitet = new Aktivitet("TestaktivitetTilTestProjekt");
	    Currentprojekt.tilfoejAktivitet(Currentaktivitet);
	    Currentaktivitet.setStartUge(ugenr);Currentaktivitet.setSlutUge(ugenr);
	    
	    Currentaktivitet.SaetMedarbejder(Currentmedarbejder);
	    
	    
	}

	@Then("{string} har ikke faaet denne aktivitet")
	public void harIkkeFaaetDenneAktivitet(String string) {
		assertFalse(Currentmedarbejder == Currentaktivitet.Medarbejder());
	}
	
	//Scenario: medarbejder melder ferie i uge hvor han har 0 aktiviteter
	@Given("der er en medarbejder {string}")
	public void derErEnMedarbejder(String medarbejderNavn) {
	    Currentmedarbejder = new Medarbejder(medarbejderNavn);
	    Currentmedarbejder.Gem();
	}

	@Given("{string} har {int} aktiviteter i uge {int}")
	public void harAktiviteterIUge(String medarbejdernavn, Integer antalAktiviteter, Integer ugenummer) {
	    Currentprojekt=new Projekt("ProjektMedXAktiviteter");Currentprojekt.Gem();
	    for (int i = 0; i < antalAktiviteter; i++) {
	    	Currentaktivitet = new Aktivitet("Xaktivitet" + i);
	    	Currentaktivitet.setTidsperiode(ugenummer, ugenummer, 2020, 2020);
	    	Currentaktivitet.SaetMedarbejder(Currentmedarbejder);
	    	assertTrue(Currentprojekt.tilfoejAktivitet(Currentaktivitet));
	    	
	    }
	    System.out.println(medarbejderManager.AktiviteterIDenneUge(ugenummer, 2020, Currentmedarbejder));
		System.out.println(aktivitetManager.AlleAktiviteterEfterMedarbejder(Currentmedarbejder).size() + " = " + antalAktiviteter);
		assertTrue(aktivitetManager.AlleAktiviteterEfterMedarbejder(Currentmedarbejder).size()==antalAktiviteter);
	}

	@When("{string} melder ferie i uge {int}")
	public void melderFerieIUge(String string, Integer int1) {
		// Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("{string} er optaget af ferie i uge {int}")
	public void erOptagetAfFerieIUge(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	//Scenario: medarbejder  melder ferie i uge hvor han har aktiviteter
	@Then("{string} har ikke faaet ferie u uge {int}")
	public void harIkkeFaaetFerieUUge(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	//OPRET NY MEDARBEJDER
	@Given("Der findes ikke en medarbejder med navnet {string}")
	public void derFindesIkkeEnMedarbejderMedNavnet(String medarbejdernavn) {
		assertFalse(medarbejderManager.eksistererMedNavn(medarbejdernavn));
	}

	@Then("Der kan oprettes en medarbejder med navnet {string}")
	public void derKanOprettesEnMedarbejderMedNavnet(String medarbejdernavn) {
	    Currentmedarbejder = new Medarbejder (medarbejdernavn);
	    assertTrue(Currentmedarbejder.Gem());
	    assertTrue(medarbejderManager.eksistererMedNavn(medarbejdernavn));
 	}

	@Given("Der er en medarbejder med navnet {string}")
	public void derErEnMedarbejderMedNavnet(String medarbejdernavn) {
	    if (!medarbejderManager.eksistererMedNavn(medarbejdernavn)) {
	    	Currentmedarbejder = new Medarbejder(medarbejdernavn);
	    	Currentmedarbejder.Gem();
	    }
	    assertTrue(medarbejderManager.eksistererMedNavn(medarbejdernavn));
	}

	@Then("Der kan ikke oprettes en ny medarbejder med navnet {string}")
	public void derKanIkkeOprettesEnNyMedarbejderMedNavnet(String medarbejdernavn) {
	    Medarbejder KanIkkeOprettes = new Medarbejder(medarbejdernavn);
	    assertFalse(KanIkkeOprettes.Gem());
	}	
}
