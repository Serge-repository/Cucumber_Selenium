package cucumber_step_defs;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static cucumber_step_defs.RunnerTest.driver;
import static cucumber_step_defs.RunnerTest.wait;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class FileUploadDef {

    @After
    public void afterScenario() {
        driver.quit();
    }

    @Given("user is on uploadFilePage")
    public void userIsOnUploadFilePage() {
        driver.navigate().to("http://demo.guru99.com/test/upload/");
    }

    @And("title of page is {string}")
    public void titleOfPageIs(String pageTitle) {
        assertEquals("Page title is File Upload Demo", pageTitle, driver.getTitle());
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