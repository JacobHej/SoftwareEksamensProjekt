package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Domaeneklasser.*;
import java.util.Date;

public class aktivitetSteps {
	Projekt projekt;
	
	@Given("der er et projekt med lederen leder med idet id {string}")
	public void derErEtProjektMedLederenLederMedIdetId(String string) {
	    //opret projekt med leder "string";
		Medarbejder leder = new Medarbejder();
		Date startDate = new Date();
		Projekt projekt = new Projekt("string", startDate, leder);
		assertTrue(true); //¯\_(ツ)_/¯
	}
	
	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String string) {
		assertTrue(projekt.tilføjAktivitet(new Aktivitet(string,projekt)));
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("projektet har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String string) {
//		assertTrue(Aktivitet findes i projektet)
	    throw new io.cucumber.java.PendingException();
	}
	
}
