package cucumber_step_defs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import pages.TestBasis;

import static org.testng.Assert.assertEquals;

public class NavigateStepDef extends TestBasis {

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
            assertEquals(username, "1303", "We enter correct username");
            assertEquals(password, "Guru99", "We enter correct password");
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
        assertEquals(message, "Welcome To Customer's Page of Guru99 Bank", "Welcome message is positive");
    }

    @Then("invalid credentials message is shown")
    public void invalidCredentialsMessageIsShown() {
        String message = driver.switchTo().alert().getText();
        assertEquals(message, "User or Password is not valid", "Welcome message is negative");
    }

    @After
    public void actionsAfter() {
        driver.quit();
    }
}
