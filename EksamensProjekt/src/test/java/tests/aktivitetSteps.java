package test.java.tests;

import static org.junit.Assert.assertFalse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class aktivitetSteps {
	
	@Given("der er et projekt med lederen leder med idet id {string}")
	public void derErEtProjektMedLederenLederMedIdetId(String string) {
	    //assertTrue(Projekt.Leder() == string));
		assertFalse(false);
	}
	
	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String string) {
		//assertFalse(aktivitet med navn string findes allerede)
		//**Opret ny aktivitet**
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("projektet har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String string) {
//		assertTrue(Aktivitet findes i projektet)
	    throw new io.cucumber.java.PendingException();
	}
	
}
