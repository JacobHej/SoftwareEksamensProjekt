package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Domaeneklasser.*;
import java.util.Date;

public class aktivitetSteps {
	static Medarbejder leder;
	static Projekt projekt;
	static Aktivitet aktivitet;
	static Date startdate;
	String aktivitetID;
	
	
	@Given("der er et projekt {string} med lederen {string}")
	public void derErEtProjektMedLederen(String projektID, String lederID) {
		leder = new Medarbejder(lederID);
		Date startDate = new Date();
		projekt = new Projekt(projektID, startDate, leder);
		//¯\_(ツ)_/¯
	}

	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String aktivitetID) {
		this.aktivitetID=aktivitetID;
		assertTrue(projekt.tilfoejAktivitet(this.aktivitetID));
	}
	
	@Then("projektet har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String aktivitetID) {
		System.out.println(AktivitetData.Bibliotek.containsValue(aktivitetID));
	}
	
}
