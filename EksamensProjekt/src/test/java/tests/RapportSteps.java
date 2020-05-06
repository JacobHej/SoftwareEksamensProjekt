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

public class RapportSteps {
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	Rapport Rapport;
	Projekt Currentprojekt;
	Aktivitet Currentaktivitet;
	Medarbejder Currentmedarbejder;
	Medarbejder CurrentLeder;
	
	@Given("Der er et projekt {string} som har projektlederen {string}")
	public void derErEtProjektSomHarProjektlederen(String projektnavn, String ledernavn) {
		Currentprojekt = projektManager.projektUdFraNavn(projektnavn);
		if (Currentprojekt == null) {
			Currentprojekt = new Projekt(projektnavn); Currentprojekt.Gem();
		}
		CurrentLeder = medarbejderManager.MedarbejderUdFraNavn(ledernavn);
		if (CurrentLeder == null) {
			CurrentLeder = new Medarbejder (ledernavn); CurrentLeder.Gem();
		}
		Currentprojekt.setStartUge(1);Currentprojekt.setStartaar(2020);
	}

	@Given("projektet {string} har aktiviteten {string}")
	public void projektetHarAktiviteten(String projektnavn, String aktivitetnavn) {
		Currentaktivitet = aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetnavn);
		if (Currentaktivitet == null){
			Currentaktivitet= new Aktivitet(aktivitetnavn);
			assertTrue(Currentprojekt.tilfoejAktivitet(Currentaktivitet));
		}
		assertTrue(Currentaktivitet.setTidsperiode(10, 20, 2020, 2020));
		assertTrue(aktivitetManager.eksisterer(Currentaktivitet));
	}

	@Given("Projektlederen som ikke er tildelt en aktivitet melder at have arbejdet {int} timer paa {string}")
	public void projektlederenSomIkkeErTildeltEnAktivitetMelderAtHaveArbejdetTimerPaa(Integer antalTimer, String aktivitetnavn) {
	    assertTrue(aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetnavn).TilfoejTid(antalTimer, CurrentLeder));
	}

	@Given("Der er en medarbejder {string} som er tildelt aktiviteten {string}")
	public void derErEnMedarbejderSomErTildeltAktiviteten(String medarbejdernavn, String aktivitetnavn) {
	    Currentmedarbejder = medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn);
	    if (Currentmedarbejder == null){
	    	Currentmedarbejder = new Medarbejder(medarbejdernavn);Currentmedarbejder.Gem();
	    }
	    Currentaktivitet = aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetnavn);

		System.out.println(medarbejderManager.MedarbejderLedig(Currentaktivitet.getStartUge(), Currentaktivitet.getSlutUge(), Currentaktivitet.getStartaar(), Currentaktivitet.getSlutaar(), Currentmedarbejder) + " TJEK!");
	    assertTrue(Currentaktivitet.SaetMedarbejder(Currentmedarbejder));
	}

	@Given("Medarbejderen {string} registrerer {int} timer paa aktiviteten {string}")
	public void medarbejderenRegistrererTimerPaaAktiviteten(String medarbejdernavn, Integer antalTimer, String aktivitetnavn) {
	    assertTrue(aktivitetManager.AktivitetEfterProjektOgNavn(Currentprojekt, aktivitetnavn).TilfoejTid(antalTimer, medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn)));
	}

	@When("Der laves en rapport over projektet {string}")
	public void derLavesEnRapportOverProjektet(String projektnavn) {
	    Currentprojekt = projektManager.projektUdFraNavn(projektnavn);
	    if(Currentprojekt == null) {
	    	Currentprojekt = projektManager.projektUdFraNavn(projektnavn); 
	    }
	    System.out.println(projektManager.eksisterer(Currentprojekt));
	    Rapport.GenererRapport(Currentprojekt);
	}

	@Then("Det fremgaar af rapporten, at der er {int} aktiviteter i projektet med navne {string} og {string}")
	public void detFremgaarAfRapportenAtDerErAktiviteterIProjektetMedNavneOg(Integer antalAktiviteter, String aktivitetnavn1, String aktivitetnavn2) {
		assertTrue(Rapport.AktivitetsInformationer().size()==antalAktiviteter);
		assertTrue((Rapport.AktivitetsInformationer().get(1).Aktivitet().getNavn() + "" + Rapport.AktivitetsInformationer().get(2).Aktivitet().getNavn()).contains(aktivitetnavn1));
		assertTrue((Rapport.AktivitetsInformationer().get(1).Aktivitet().getNavn() + "" + Rapport.AktivitetsInformationer().get(2).Aktivitet().getNavn()).contains(aktivitetnavn2));
	}

	@Then("{string} er blevet tildelt medarbejderen {string} og {string} har ingen medarbejder tildelt")
	public void erBlevetTildeltMedarbejderenOgHarIngenMedarbejderTildelt(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Der er {int} timer registreret paa aktiviteten {string} og {int} timer paa aktiviteten {string}")
	public void derErTimerRegistreretPaaAktivitetenOgTimerPaaAktiviteten(Integer int1, String string, Integer int2, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
