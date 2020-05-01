package test.java.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Applikationslag.Data.Datavedholdelsesklasser.AktivitetData;
import Applikationslag.Domaeneklasser.*;
import Applikationslag.Infrastruktur.ServiceInterfaces.IProjektManager;
import Applikationslag.Infrastruktur.ServiceImplementationer.ProjektManager;
import Applikationslag.Infrastruktur.ServiceInterfaces.IMedarbejderManager;
import Applikationslag.Redskaber.Managers;

import java.util.Date;

public class projektSteps {
	Medarbejder medarbejder;
	Projekt projekt;
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	
	@Given("der er en medarbejder med ID'et {string}")
	public void derErEnMedarbejderMedIDEt(String MedarbejderID) {
		medarbejder=new Medarbejder(MedarbejderID);					//opret ny medarbjder
	}

	@When("Medarbejderen opretter et projekt med ID'et {string}")
	public void medarbejderenOpretterEtProjektMedIDEt(String projektID) {
	    projekt = new Projekt(projektID);
	    projekt.Gem();
	}

	@Then("Der findes et nyt projekt med ID'et {string}")
	public void derFindesEtNytProjektMedIDEt(String projektID) {
		assertTrue(projekt.getNavn()==projektID);
	    assertTrue(projektManager.eksisterer(projekt));

	}


}
