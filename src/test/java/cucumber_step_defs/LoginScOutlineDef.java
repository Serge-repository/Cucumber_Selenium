package cucumber_step_defs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static cucumber_step_defs.RunnerTest.driver;
import static org.junit.Assert.assertEquals;

public class LoginScOutlineDef {

    @After
    public void afterScenario() {
        driver.quit();
    }

    @Given("homepage opened")
    public void homepageOpened() {
        driver.navigate().to("http://demo.guru99.com/");
    }

    @When("click on agile page")
    public void clickOnAgilePage() {
        driver.findElement(By.xpath("//a[contains(@href,'Agile_Project')]")).click();
    }

    @And("^enter (\\d+) and ([^\"]*)$")     // ([^"]*) - текст ||   (\\d+) - числа
    public void enterUsernameAndPassword(String username, String password) {
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        assertEquals("Checking username correctness", username, "1303");
        assertEquals("Checking password correctness", password, "Guru99");
    }

    @And("press login button")
    public void pressLoginButton() {
        driver.findElement(By.name("btnLogin")).click();
    }

    @Then("^welcome message should be ([^\"]*)$")
    public void welcomeMessageShouldBeExpectedMessage(String expectedMessage) {
        String message = driver.findElement(By.tagName("marquee")).getText();
        assertEquals("Result message test", message, expectedMessage);
    }
}