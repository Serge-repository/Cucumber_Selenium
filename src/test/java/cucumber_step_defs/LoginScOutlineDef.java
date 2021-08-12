package cucumber_step_defs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class LoginScOutlineDef {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void beforeScenario(Scenario scenario) {
        if (scenario.getName().equals("Login as a authenticated user")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.getName().equals("Login as a authenticated user")){
            driver.quit();
        }
    }

    @Given("homepage opened")
    public void homepageOpened() {
        driver.navigate().to("http://demo.guru99.com/");
    }

    @When("click on agile page")
    public void clickOnAgilePage() {
        driver.findElement(By.xpath("//a[contains(@href,'Agile_Project')]")).click();
    }

    @And("^enter ([^\"]*) and ([^\"]*)$")
    public void enterUsernameAndPassword(String username, String password) {
        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
            assertEquals(username, "1303", "Checking username correctness");
            assertEquals(password, "Guru99", "Checking password correctness");
    }

    @And("press login button")
    public void pressLoginButton() {
        driver.findElement(By.name("btnLogin")).click();
    }

    @Then("^welcome message should be ([^\"]*)$")
    public void welcomeMessageShouldBeExpectedMessage(String expectedMessage) {
        String message = driver.findElement(By.tagName("marquee")).getText();
        assertEquals(message, expectedMessage, "Result message test");
    }
}