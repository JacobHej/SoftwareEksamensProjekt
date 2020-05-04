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
	    assertTrue(projekt.getProjektnummer()>0);
	}
	
	/*******AEndre i projektdata (Her udnytter vi bibliotekets lagring af data)********/
	Projekt Currentprojekt;
	Aktivitet Currentaktivitet;
	
	@When("Projektets navn aendres fra {string} til {string}")
	public void projektetsNavnAendresFraTil(String GammeltNavn, String NytNavn) {
		System.out.println(projektManager.projektUdFraNavn(GammeltNavn));
		Currentprojekt = (projektManager.projektUdFraNavn(GammeltNavn));
		System.out.println(Currentprojekt.getNavn());
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
	    assertTrue(Currentprojekt.getLeder().getNavn().equals(nylederNavn));
	}

	@When("Projektet faar sat sin starttid til uge {int} aar {int}")
	public void projektetFaarSatSinStarttidTilUgeAar(Integer int1, Integer int2) {
	    Currentprojekt.setStartUge(int1);
	    Currentprojekt.setStartaar(int2);
	}

	@Then("Projektets starttid er uge {int} aar {int}")
	public void projektetsStarttidErUgeAar(Integer int1, Integer int2) {
		assertTrue(Currentprojekt.getStartUge()==(int1));
	    assertTrue(Currentprojekt.getStartaar()==(int2));
	}
	
	@When("Projektlederen sletter aktiviteten {string} fra projektet {string}")
	public void projektlederenSletterAktivitetenFraProjektet(String aktivitetsNavn, String projektNavn) {
		Currentprojekt = (projektManager.projektUdFraNavn(projektNavn));
		Currentaktivitet = aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetsNavn);
		assertTrue(aktivitetManager.fjern(Currentaktivitet));
	}

	@Then("Projektet har ikke laengere aktiviteten {string}")
	public void projektetHarIkkeLaengereAktiviteten(String string) {
	    assertFalse(aktivitetManager.eksisterer(Currentaktivitet));
	}
	
	@Given("aktiviteten {string} i projektet {string} har {int} timer registreret")
	public void aktivitetenIProjektetHarTimerRegistreret(String AktivitetNavn, String ProjektNavn, Integer int1) {
	    Currentprojekt=projektManager.projektUdFraNavn(ProjektNavn);
	    Currentaktivitet=aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, AktivitetNavn);
	    this.medarbejder=new Medarbejder("Hans");
	    assertTrue(this.medarbejder.Gem());
	    assertTrue(Currentaktivitet.SaetMedarbejder(this.medarbejder));
		assertTrue(Currentaktivitet.TilfoejTid(int1));
	}

	@When("Projektlederen sletter aktiviteten {string}")
	public void projektlederenSletterAktiviteten(String string) {
	    assertFalse(aktivitetManager.fjern(Currentaktivitet));
	}

	@Then("aktiviteten {string} er ikke slettet")
	public void aktivitetenErIkkeSlettet(String aktivitetNavn) {
	    assertTrue(aktivitetManager.eksisterer(Currentaktivitet));
	    assertTrue(aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetNavn)
	    		==Currentaktivitet);
	}
	
	//**********************************************************************
	Medarbejder Currentmedarbejder;
	
	@Given("en medarbjeder {string} som har {int} aktiviteter i uge {int}")
	public void enMedarbjederSomHarAktiviteterIUge(String medarbejederNavn, Integer AktiviteterUge2, Integer Uge2) {
	    Currentmedarbejder = new Medarbejder(medarbejederNavn);
	    Currentmedarbejder.Gem();
	    Projekt Filler = new Projekt("Fillerprojekt for" + Currentmedarbejder.getNavn());
	    Filler.Gem();
	    for (int i = 0; i < AktiviteterUge2; i++){
	    	Aktivitet Fill = new Aktivitet("FillerAktivitet"  + i);
	    	Filler.tilfoejAktivitet(Fill);
	    	Fill.SaetMedarbejder(Currentmedarbejder);
	    	Fill.setStartaar(2020);Fill.setSlutaar(2020);
	    	Fill.setStartUge(Uge2);Fill.setSlutUge(Uge2);
	    }
	    System.out.println(Currentmedarbejder.getNavn() + " " + medarbejderManager.AktiviteterIDenneUge(Uge2, 2020, Currentmedarbejder));
	    assertTrue((medarbejderManager.AktiviteterIDenneUge(Uge2, 2020, Currentmedarbejder))==AktiviteterUge2);

	}

	@Given("medarbejderen {string} har {int} aktivitet {string} uge {int} i projektet {string}")
	public void medarbejderenHarAktivitetUgeIProjektet(String medarbjederNavn, Integer AktiviteterUge1, String AktivitetNavn, Integer Uge1, String ProjektnavnUge1) {
	    Projekt Uge1Projekt = new Projekt (ProjektnavnUge1);
	    Uge1Projekt.Gem();
	    Aktivitet AktivitetUge1 = new Aktivitet(AktivitetNavn);
	    Uge1Projekt.tilfoejAktivitet(AktivitetUge1);
	    AktivitetUge1.setStartaar(2020);AktivitetUge1.setSlutaar(2020);
	    AktivitetUge1.setStartUge(Uge1);AktivitetUge1.setSlutUge(Uge1);
	    assertTrue(AktivitetUge1.SaetMedarbejder(Currentmedarbejder));
	    
	    assertTrue((medarbejderManager.AktiviteterIDenneUge(Uge1, 2020, Currentmedarbejder))==1);
	}

	@When("Der forsoeges at Aktiviteten {string} i projekt {string} faar sin slut-uge aendret til uge {int}")
	public void derForsoegesAtAktivitetenIProjektFaarSinSlutUgeAendretTilUge(String AktivitetNavn, String ProjektNavn, Integer Uge2) {
		Currentaktivitet = aktivitetManager.AktivitetEfterProjektOgNavn(projektManager.projektUdFraNavn(ProjektNavn), AktivitetNavn);
		Currentaktivitet.setSlutUge(Uge2);
	}

	@Then("Aktiviteten er ikke flyttet.")
	public void aktivitetenErIkkeFlyttet() {
	    assertTrue(Currentaktivitet.getSlutUge()==1);
	    
	}
	//**********************************************************************
	
	@Given("Der er et projekt {string} som har starttid uge {int} aar {int}")
	public void derErEtProjektSomHarStarttidUgeAar(String projektnavn, Integer startuge, Integer startaar) {
		Currentprojekt= new Projekt(projektnavn);
		Currentprojekt.Gem();
		Currentprojekt.setStartaar(startaar);Currentprojekt.setStartUge(startuge);
	}

	@Given("projektet har en aktivitet {string}")
	public void projektetHarEnAktivitet(String AktivitetNavn) {
	    Currentaktivitet = new Aktivitet(AktivitetNavn);
	    
	    Currentaktivitet.setStartaar(Currentprojekt.getStartaar());
	    Currentaktivitet.setSlutaar(Currentprojekt.getStartaar());
	    Currentaktivitet.setStartUge(Currentprojekt.getStartUge());
	    Currentaktivitet.setSlutUge(Currentprojekt.getStartUge());
	    
	    Currentprojekt.tilfoejAktivitet(Currentaktivitet); 
	    assertTrue(Currentaktivitet.getProjekt()==Currentprojekt);
	}

	@When("{string} er tildelt aktiviteten")
	public void erTildeltAktiviteten(String MedarbejderNavn) {
		Currentmedarbejder=medarbejderManager.MedarbejderUdFraNavn(MedarbejderNavn);
		Currentaktivitet.SaetMedarbejder(Currentmedarbejder);

	    assertTrue(Currentaktivitet.Medarbejder()==Currentmedarbejder);
	}

	@Then("{string} kan se, at han\\/hun har et projekt i uge {int} aar {int}")
	public void kanSeAtHanHunHarEtProjektIUgeAar(String medarbejderNavn, Integer Ugenr, Integer aar) {
		assertTrue(medarbejderManager.AktiviteterIDenneUge(Ugenr, aar, Currentmedarbejder)==1);

	}
}
