package tests;

import static org.junit.Assert.assertFalse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class aktivitetSteps {
	
	@Given("der er et projekt med lederen leder med idet id {string}")
	public void derErEtProjektMedLederenLederMedIdetId(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertFalse(false);
	}
	
	@When("lederen laver en ny aktivitet med navnet {string}")
	public void lederenLaverEnNyAktivitetMedNavnet(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("projektet har en ny aktivitet med navnet {string}")
	public void projektetHarEnNyAktivitetMedNavnet(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
}
