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

public class InformationSteps {
	IMedarbejderManager medarbejderManager= Managers.FaaMedarbejderManager();
	IProjektManager projektManager= Managers.FaaProjektManager();
	IAktivitetManager aktivitetManager= Managers.FaaAktivitetManager();
	Medarbejder Currentmedarbejder;
	Aktivitet Currentaktivitet;
	Projekt Currentprojekt;
	String TestString = "";
	
	@Given("Der er en medarbejder {string} som er ledig i uge {int}")
	public void derErEnMedarbejderSomErLedigIUge(String medarbejdernavn, Integer ugenummer) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("Der er en medarbejder som {string} som ikke er ledig i uge {int}")
	public void derErEnMedarbejderSomSomIkkeErLedigIUge(String string, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Der spoerges om ledige medarbejdere i uge {int}")
	public void derSpoergesOmLedigeMedarbejdereIUge(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("{string} er i listen af ledige medarbejdere")
	public void erIListenAfLedigeMedarbejdere(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("{string} er ikke i listen af ledige medarbejdere")
	public void erIkkeIListenAfLedigeMedarbejdere(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	//************
	@Given("{string} har en aktivitet {string} i uge {int}")
	public void harEnAktivitetIUge(String medarbejdernavn, String aktivitetnavn, Integer ugenummer) {
	    Currentmedarbejder = medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn);
	    	    
	    if (!projektManager.eksisterer(projektManager.projektUdFraNavn("Unavngivet Projekt"))) {	
	    	Currentprojekt = new Projekt("Unavngivet Projekt");Currentprojekt.Gem();
	    }else {Currentprojekt=projektManager.projektUdFraNavn("Unavngivet Projekt");}
	    
	    Currentaktivitet = new Aktivitet(aktivitetnavn);assertTrue(Currentaktivitet.setTidsperiode(ugenummer, ugenummer, 2020, 2020));
	    Currentprojekt.tilfoejAktivitet(Currentaktivitet);
	    assertTrue(Currentaktivitet.SaetMedarbejder(Currentmedarbejder));
	}

	@When("Der spoerges om hvornaar {string} har aktiviteter")
	public void derSpoergesOmHvornaarHarAktiviteter(String medarbejdernavn) {
		assertTrue(Currentmedarbejder == medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn));
		this.TestString="";
		for (int i = 0; i < Currentmedarbejder.getAlleAktiviteter().size(); i++) {
			this.TestString=this.TestString
					+Currentmedarbejder.getAlleAktiviteter().get(i).getValue().getStartUge() + "-" +
					+Currentmedarbejder.getAlleAktiviteter().get(i).getValue().getSlutUge() + ":" + 
					+Currentmedarbejder.getAlleAktiviteter().get(i).getValue().getStartaar() + "-"
					+Currentmedarbejder.getAlleAktiviteter().get(i).getValue().getSlutaar() + ",";
		}
	}

	@Then("Svaret er uge {int} og {int}")
	public void svaretErUgeOg(Integer int1, Integer int2) {
		//System.out.println(TestString);
	    assertTrue(this.TestString.equals("4-4:2020-2020,5-5:2020-2020,")||this.TestString.equals("5-5:2020-2020,4-4:2020-2020,"));
	}
	
	//****************
	@Given("vi kigger igen paa medarbejderen {string}")
	public void viKiggerIgenPaaMedarbejderen(String medarbejdernavn) {
		Currentmedarbejder = medarbejderManager.MedarbejderUdFraNavn(medarbejdernavn);
	    assertTrue(Currentmedarbejder.getNavn().equals(medarbejdernavn));
	}
	
	@Given("{string} faar endnu en aktivitet {string} i uge {int}")
	public void faarEndnuEnAktivitetIUge(String medarbejdernavn, String aktivitetnavn, Integer ugenummer) {
	    Currentprojekt=projektManager.projektUdFraNavn("Unavngivet Projekt");
	    Currentaktivitet = new Aktivitet(aktivitetnavn);Currentprojekt.tilfoejAktivitet(Currentaktivitet);
	    Currentaktivitet.setTidsperiode(ugenummer, ugenummer, 2020, 2020);
	    assertTrue(Currentaktivitet.SaetMedarbejder(Currentmedarbejder));
	}

	@Then("det kan ses at {string} har {int} aktivitet i uge {int} og {int} aktiviteter i uge {int}")
	public void detKanSesAtHarAktivitetIUgeOgAktiviteterIUge(String medarbejdernavn, Integer int1, Integer int2, Integer int3, Integer int4) {
		derSpoergesOmHvornaarHarAktiviteter(medarbejdernavn);
		System.out.println(this.TestString);
		assertTrue(TestString.contains("5-5:2020-2020,5-5:2020-2020"));
		assertTrue(TestString.contains("4-4:2020-2020"));
	}
	
	//***********
	@Then("det ses at {string} har aktiviteterme {string} og {string} og {string}")
	public void detSesAtHarAktivitetermeOgOg(String medarbejdernavn, String aktivitetnavn1, String aktivitetnavn2, String aktivitetnavn3) {
	    this.TestString="";
		aktivitetManager.AlleAktiviteterEfterMedarbejder(Currentmedarbejder);
	    for (int i = 0; i <aktivitetManager.AlleAktiviteterEfterMedarbejder(Currentmedarbejder).size(); i++) {
	    	this.TestString=this.TestString+aktivitetManager.AlleAktiviteterEfterMedarbejder(Currentmedarbejder).get(i).getValue().getNavn() + " ";
	    }
	    
	    assertTrue(this.TestString.contains(aktivitetnavn1)
	    		&& this.TestString.contains(aktivitetnavn2)
	    		&& this.TestString.contains(aktivitetnavn3));
	}
	
}
