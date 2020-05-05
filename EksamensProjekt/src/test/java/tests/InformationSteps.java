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
	
	
	@Given("Der er en medarbejder {string} som er ledig i uge {int}")
	public void derErEnMedarbejderSomErLedigIUge(String string, Integer int1) {
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
	public void harEnAktivitetIUge(String string, String string2, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("Der spoerges om hvornaar {string} har aktiviteter")
	public void derSpoergesOmHvornaarHarAktiviteter(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Svaret er uge {int} og {int}")
	public void svaretErUgeOg(Integer int1, Integer int2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	//****************
	@Given("vi kigger igen paa medarbejderen {string}")
	public void viKiggerIgenPaaMedarbejderen(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("{string} faar endnu en aktivitet {string} i uge {int}")
	public void faarEndnuEnAktivitetIUge(String string, String string2, Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("det kan ses at {string} har {int} aktivitet i uge {int} og {int} aktiviteter i uge {int}")
	public void detKanSesAtHarAktivitetIUgeOgAktiviteterIUge(String string, Integer int1, Integer int2, Integer int3, Integer int4) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	//***********
	@Then("det ses at {string} har aktiviteterme {string} og {string} og {string}")
	public void detSesAtHarAktivitetermeOgOg(String string, String string2, String string3, String string4) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
