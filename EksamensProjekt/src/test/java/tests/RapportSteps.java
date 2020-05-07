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
	Rapport Rapport = new Rapport();
	Projekt Currentprojekt;
	Aktivitet Currentaktivitet;
	Medarbejder Currentmedarbejder;
	Medarbejder CurrentLeder;
	
	@Given("Der er et projekt {string} som har projektlederen {string}")
	public void derErEtProjektSomHarProjektlederen(String projektnavn, String ledernavn) {
		Currentprojekt = projektManager.projektUdFraNavn(projektnavn);
		if (Currentprojekt == null) {
			Currentprojekt = new Projekt(projektnavn); 
			Currentprojekt.Gem();
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
	    System.out.println(projektManager.eksisterer(Currentprojekt) + "<---");
	    System.out.println(Currentprojekt.getAlleAktiviteter().get(0).getValue().getNavn());
	    Rapport.GenererRapport(Currentprojekt);
	}

	@Then("Det fremgaar af rapporten, at der er {int} aktiviteter i projektet med navne {string} og {string}")
	public void detFremgaarAfRapportenAtDerErAktiviteterIProjektetMedNavneOg(Integer antalAktiviteter, String aktivitetnavn1, String aktivitetnavn2) {
		assertTrue(Rapport.AktivitetsInformationer().size()==antalAktiviteter);
		assertTrue((Rapport.AktivitetsInformationer().get(0).Aktivitet().getNavn() + "" + Rapport.AktivitetsInformationer().get(1).Aktivitet().getNavn()).contains(aktivitetnavn1));
		assertTrue((Rapport.AktivitetsInformationer().get(0).Aktivitet().getNavn() + "" + Rapport.AktivitetsInformationer().get(1).Aktivitet().getNavn()).contains(aktivitetnavn2));
	}

	@Then("{string} er blevet tildelt medarbejderen {string} og {string} har ingen medarbejder tildelt")
	public void erBlevetTildeltMedarbejderenOgHarIngenMedarbejderTildelt(String aktivitetnavn1, String medarbejdernavn, String aktivitetnavn2) {
	    Currentmedarbejder = medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn);
	    
	    assertTrue((
	    		Rapport.AktivitetsInformationer().get(0).Aktivitet().getNavn().equals(aktivitetnavn1)&&
	    		Rapport.AktivitetsInformationer().get(1).Aktivitet().getNavn().equals(aktivitetnavn2)&&
	    		Rapport.AktivitetsInformationer().get(0).Aktivitet().Medarbejder().getNavn().equals(medarbejdernavn)&&
	    		Rapport.AktivitetsInformationer().get(1).Aktivitet().Medarbejder()==null
	    		)||
	    		Rapport.AktivitetsInformationer().get(1).Aktivitet().getNavn().equals(aktivitetnavn1)&&
	    		Rapport.AktivitetsInformationer().get(0).Aktivitet().getNavn().equals(aktivitetnavn2)&&
	    		Rapport.AktivitetsInformationer().get(1).Aktivitet().Medarbejder().getNavn().equals(medarbejdernavn)&&
	    		Rapport.AktivitetsInformationer().get(0).Aktivitet().Medarbejder()==null);

	}

	@Then("Der er {int} timer registreret paa aktiviteten {string} og {int} timer paa aktiviteten {string}")
	public void derErTimerRegistreretPaaAktivitetenOgTimerPaaAktiviteten(Integer antalTimer1, String aktivitetnavn1, Integer antalTimer2, String aktivitetnavn2) {
		String TestString = "";
	    for (int i = 0; i < Rapport.AktivitetsInformationer().size(); i++) {
	    	TestString=TestString + Rapport.AktivitetsInformationer().get(i).Aktivitet().getNavn() + ":" + Rapport.AktivitetsInformationer().get(i).Aktivitet().getTidBrugt() + "\n";
	    }
	    System.out.println(Rapport.AktivitetsInformationer().get(0).Aktivitet().getTidBrugt());

	    System.out.println(TestString);
	    assertTrue(TestString.contains(aktivitetnavn1+ ":" + antalTimer1));
	    assertTrue(TestString.contains(aktivitetnavn2+ ":" + antalTimer2));
	}
}
