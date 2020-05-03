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

public class projektSteps {
	Medarbejder medarbejder;
	Projekt projekt;
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	
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
	Projekt Currentprojekt;
	Aktivitet Currentaktivitet;
	
	@When("Projektets navn aendres fra {string} til {string}")
	public void projektetsNavnAendresFraTil(String GammeltNavn, String NytNavn) {
		Currentprojekt = (projektManager.projektUdFraNavn(GammeltNavn));
		Currentprojekt.setNavn(NytNavn);
	}

	@Then("Projektets navn er {string}")
	public void projektetsNavnEr(String NytNavn) {
	    assertTrue(Currentprojekt == projektManager.projektUdFraNavn(NytNavn));
	}

	@When("Projektet faar en ny projektleder med navnet {string}")
	public void projektetFaarEnNyProjektlederMedNavnet(String nylederNavn) {
		Medarbejder nyLeder = new Medarbejder(nylederNavn);
		nyLeder.Gem();
	    Currentprojekt.setLeder(nyLeder);
	}

	@Then("Projektets projektleder har navnet {string}")
	public void projektetsProjektlederHarNavnet(String nylederNavn) {
	    assertTrue(Currentprojekt.getLeder().Navn().equals(nylederNavn));
	}

	@When("Projektet faar sat sin starttid til uge {int} aar {int}")
	public void projektetFaarSatSinStarttidTilUgeAar(Integer int1, Integer int2) {
	    Currentprojekt.setStartUge(int1);
	    Currentprojekt.setStartÅr(int2);
	}

	@Then("Projektets starttid er uge {int} aar {int}")
	public void projektetsStarttidErUgeAar(Integer int1, Integer int2) {
		assertTrue(Currentprojekt.getStartUge()==(int1));
	    assertTrue(Currentprojekt.getStartÅr()==(int2));
	}
	
	@When("Projektlederen sletter aktiviteten {string} fra projektet {string}")
	public void projektlederenSletterAktivitetenFraProjektet(String aktivitetsNavn, String projektNavn) {
		Currentprojekt = (projektManager.projektUdFraNavn(projektNavn));
		System.out.println(aktivitetManager.AlleAktiviteterEfterProjekt(Currentprojekt));
	}

	@Then("Projektet har ikke laengere aktiviteten")
	public void projektetHarIkkeLaengereAktiviteten() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
