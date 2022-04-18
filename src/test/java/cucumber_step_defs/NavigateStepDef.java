package cucumber_step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static cucumber_step_defs.DriverInitializer.driver;
import static org.junit.Assert.assertEquals;

public class NavigateStepDef {

    @Given("user is on home page")
    public void userIsOnHomePage() {
        driver.navigate().to("http://demo.guru99.com/");
    }

    @When("user navigates to agile page")
    public void userNavigatesToAgilePage() {
        driver.findElement(By.xpath("//a[contains(@href,'Agile_Project')]")).click();
    }

    @And("user enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        if (username.equals("1303") && password.equals("Guru99")) {
            assertEquals("We enter correct username", username, "1303");
            assertEquals("We enter correct password", password, "Guru99");
        } else {
            System.out.println("Username or password is incorrect");
        }
    }

    @And("click login button")
    public void clickLoginButton() {
        driver.findElement(By.name("btnLogin")).click();
    }

    @Then("welcome message is correct")
    public void welcomeMessageIsCorrect() {
        String message = driver.findElement(By.tagName("marquee")).getText();
        assertEquals("Welcome message is positive", message, "Welcome To Customer's Page of Guru99 Bank");
    }

    @Then("invalid credentials message is shown")
    public void invalidCredentialsMessageIsShown() {
        String message = driver.switchTo().alert().getText();
        assertEquals("Welcome message is negative", message, "User or Password is not valid");
    }
}
