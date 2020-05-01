package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Domaeneklasser.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.IAktivitetManager;
import Applikationslag.Redskaber.Managers;

import java.util.Date;

public class aktivitetSteps {
	static Medarbejder leder;
	static Projekt projekt;
	static Aktivitet aktivitet;
	static Date startdate;
	String aktivitetID;
	String projektNavn;
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	
	
	@Given("der er et projekt {string} med lederen {string}")
	public void derErEtProjektMedLederen(String projektID, String lederID) {
		this.projektNavn=projektNavn;
		leder = new Medarbejder(lederID);
		projekt = new Projekt(projektNavn);
		projekt.Gem();
		//¯\_(ツ)_/¯
	}

	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String aktivitetID) {
		aktivitet= new Aktivitet(aktivitetID);
		assertTrue(projekt.tilfoejAktivitet(aktivitet));
	}
	
	@Then("projektet {string} har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String projektNavn, String aktivitetID) {
		assertTrue(aktivitetManager.eksisterer(aktivitet));
		assertTrue(aktivitet.getProjekt() == projekt);
	}
	
	
	// Forsoeger at tilfoeje aktivitet som allerede findes i projektet
	@Given("Projektet {string} har aktiviteten {string}")
	public void projektetHarAktiviteten(String ProjektNavn, String aktivitetID) {
	    projekt = new Projekt(projektNavn);
	    projekt.Gem();										//opret projekt
	    aktivitet = new Aktivitet(aktivitetID);				//opret aktivitet
	    projekt.tilfoejAktivitet(aktivitet);				//tilfoej aktivitet til projekt
		assertTrue(aktivitetManager.eksisterer(aktivitet));	//tjek at aktiviteten findes
		assertTrue(aktivitet.getProjekt() == projekt);		//tjek at aktivitet er i projekt
	}

	@When("lederen proever at lave en ny aktivitet med navnet {string}")
	public void lederenProeverAtLaveEnNyAktivitetMedNavnet(String string) {
		assertFalse(projekt.tilfoejAktivitet(aktivitet));
	}

	@Then("Kast exception {string}")
	public void kastException(String ErrorMessage) {
//	    throw new IllegalArgumentException(ErrorMessage);
	}
	
}
