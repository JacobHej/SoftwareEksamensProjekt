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
	String projektID;
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	
	
	@Given("der er et projekt {string} med lederen {string}")
	public void derErEtProjektMedLederen(String projektID, String lederID) {
		this.projektID=projektID;
		leder = new Medarbejder(lederID);
		projekt = new Projekt(projektID);
		projekt.Gem();
		//¯\_(ツ)_/¯
	}

	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String aktivitetID) {
		aktivitet= new Aktivitet(aktivitetID);
		assertTrue(projekt.tilfoejAktivitet(aktivitet));
	}
	
	@Then("projektet {string} har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String projektID, String aktivitetID) {
		assertTrue(aktivitetManager.eksisterer(aktivitet));
		assertTrue(aktivitet.getProjekt() == projekt);
	}
	
	
	// Forsoeger at tilfoeje aktivitet som allerede findes i projektet
	@Given("Projektet {string} har aktiviteten {string}")
	public void projektetHarAktiviteten(String ProjektID, String aktivitetID) {
	    projekt = new Projekt(projektID);
	    projekt.Gem();										//opret projekt
	    aktivitet = new Aktivitet(aktivitetID);				//opret aktivitet
	    projekt.tilfoejAktivitet(aktivitet);				//tilfoej aktivitet til projekt
		assertTrue(aktivitetManager.eksisterer(aktivitet));	//tjek at aktiviteten findes
		assertTrue(aktivitet.getProjekt() == projekt);		//tjek at aktivitet er i projekt
	}

	@When("lederen proever at lave en ny aktivitet med navnet {string}")
	public void lederenProeverAtLaveEnNyAktivitetMedNavnet(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Kast exception {string}")
	public void kastException(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
