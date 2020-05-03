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
import Applikationslag.Redskaber.Managers;

import java.util.Date;
import java.util.UUID;
import java.util.Map.Entry;

public class projektSteps {
	Medarbejder medarbejder;
	Projekt projekt;
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	
	@When("Medarbejderen opretter et projekt med navnet {string}")
	public void medarbejderenOpretterEtProjektMedNavnet(String projektNavn) {
	    projekt = new Projekt(projektNavn);
	    projekt.Gem();
	}

	@Then("Der findes et nyt projekt med navnet {string}")
	public void derFindesEtNytProjektMedNavnet(String projektNavn) {
		assertTrue(projekt.getNavn().equals(projektNavn));
	    assertTrue(projektManager.eksisterer(projekt));

	}
	
	@Then("projektet {string} har et loebenummer som er tildelt af systemet")
	public void projektetHarEtLoebenummerSomErTildeltAfSystemet(String ProjektNavn) {
	    assertTrue(new String("" + projekt.getProjektnummer()).substring(0,2).equals("20"));
	}
	
	/*******AEndre i projektdata (Her udnytter vi bibliotekets lagring af data)********/
	Projekt Currentproject;
	
	@When("Projektets navn aendres fra {string} til {string}")
	public void projektetsNavnAendresFraTil(String GammeltNavn, String NytNavn) {
		//System.out.println(projektManager.AlleProjekter().get(3).getValue().getNavn());
		
		//System.out.println(projektManager.projektUdFraNavn(GammeltNavn).getNavn());
		
		
		for(Entry<UUID, Projekt> p : projektManager.AlleProjekter()) {
			System.out.println(p.getValue().getNavn());
		}
		
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Projektets navn er {string}")
	public void projektetsNavnEr(String NytNavn) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Projektet faar en ny projektleder med navnet {string}")
	public void projektetFaarEnNyProjektlederMedNavnet(String nylederNavn) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Projektets projektleder har navnet {string}")
	public void projektetsProjektlederHarNavnet(String nylederNavn) {
	    assertTrue(this.projekt.getLeder().Navn()==nylederNavn);
	}

	@When("Projektet faar sat sin starttid til uge {int} aar {int}")
	public void projektetFaarSatSinStarttidTilUgeAar(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Projektets starttid er uge {int} aar {int}")
	public void projektetsStarttidErUgeAar(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
