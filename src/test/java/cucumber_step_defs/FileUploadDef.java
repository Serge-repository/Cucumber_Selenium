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
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileUploadDef {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void beforeScenario(Scenario scenario) {
        if (scenario.getName().equals("User can upload file on uploadFilePage")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.getName().equals("User can upload file on uploadFilePage")){
            driver.quit();
        }
    }

    @Given("user is on uploadFilePage")
    public void userIsOnUploadFilePage() {
        driver.navigate().to("http://demo.guru99.com/test/upload/");
    }

    @And("title of page is {string}")
    public void titleOfPageIs(String pageTitle) {
        assertEquals(pageTitle, driver.getTitle(), "Page title is File Upload Demo");
    }

    @When("user click Choose File button")
    public void userClickButton() {
        wait.until(presenceOfElementLocated(By.id("uploadfile_0")));
        Point point = driver.findElement(By.id("uploadfile_0")).getLocation();
        int x = point.getX();
        int y = point.getY();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id("uploadfile_0")), x, y);
        actions.click();
    }

    @And("add file path")
    public void addFilePath() throws AWTException {
        Path path = Paths.get(System.getProperty("user.home"));
        String fileSeparator = FileSystems.getDefault().getSeparator();
        String pathAsString = path.toString();
        StringSelection filePath = new StringSelection(pathAsString + fileSeparator + "test.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
        Robot robot = new Robot();
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @And("click {string} button")
    public void clickButton(String submitButton) {
        assertEquals(submitButton, driver.findElement(By.id("submitbutton")).getText());
        driver.findElement(By.id("submitbutton")).click();
    }

    @Then("success file upload message is shown")
    public void successFileUploadMessageIsShown() {
        assertTrue(driver.findElement(By.xpath("//div[@class='formbuttons']/h3/center")).isDisplayed());
    }
}