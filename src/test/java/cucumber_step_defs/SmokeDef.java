package cucumber_step_defs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import pages.TestBasis;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SmokeDef extends TestBasis {
    @Given("User is on homePage")
    public void userIsOnHomePage() {
        driver.navigate().to("http://demo.guru99.com/Agile_Project/Agi_V1/");
    }

    @Then("title of homePage is {string}")
    public void titleOfHomePageIs(String pageTitle) {
        assertEquals(pageTitle, driver.getTitle(), "Home page title is Guru99 Bank Home Page");
    }

    @And("login form is present")
    public void loginFormIsPresent() {
        assertTrue(driver.findElement(By.name("btnLogin")).isDisplayed());
    }

    @When("user click on newToursButton")
    public void userClickOnNewToursButton() {
        driver.findElement(By.xpath("//a[contains(@href,'newtours')]")).click();
    }

    @Then("title of newToursPage is {string}")
    public void titleOfNewToursPageIs(String pageTitle) {
        assertEquals(pageTitle, driver.getTitle(), "Title must be Welcome: Mercury Tours");
    }

    @And("main fragment is present")
    public void mainFragmentIsPresent() {
        assertTrue(driver.findElement(By.xpath("//img[contains(@src,'images/logo.gif')]")).isDisplayed());
    }

    @When("user click on tableDemoLink")
    public void userClickOnTableDemoLink() {
        driver.findElement(By.xpath("//ul[@class='nav navbar-nav']/li")).click();
        driver.findElement(By.xpath("//a[contains(@href,'table')]")).click();
    }

    @Then("title of tablePage is {string}")
    public void titleOfTablePageIsCorrect(String tabName) {
        assertEquals(tabName, driver.getTitle(), "Title of the page is correct");
    }

    @And("table is present")
    public void tableIsPresent() {
        assertTrue(driver.findElement(By.xpath("//table")).isDisplayed());
    }

    @After
    public void actionsAfter() {
        driver.quit();
    }
}
