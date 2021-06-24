package cucumber_step_defs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.TestBasis;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DragAndDropDef extends TestBasis {

    @Given("user is on dragAndDrop page")
    public void userIsOnDragAndDropPage() {
        driver.navigate().to("http://demo.guru99.com/test/drag_drop.html");
    }

    @When("user drop debit account {string}")
    public void userDropDebitAccount(String elementText) {
        WebElement originBankElement = driver.findElement(By.cssSelector("li[id='credit2']>a"));
        WebElement destinationBankElement = driver.findElement(By.id("bank"));

        Actions action = new Actions(driver);
        action.dragAndDrop(originBankElement, destinationBankElement).build().perform();

        assertEquals(originBankElement.getText().trim(), elementText, "User drops BANK element");
        assertTrue(driver.findElement(By.xpath("//ol[@id='bank']/li[@data-id='5']")).isDisplayed(), "Bank element is displayed in a table");
    }

    @And("user drop debit amount {string}")
    public void userDropDebitAmount(String elementText) {
        WebElement originDebit = driver.findElement(By.cssSelector("#fourth:nth-child(2)>a"));
        WebElement destinationDebit = driver.findElement(By.id("amt7"));

        Actions action = new Actions(driver);
        action.dragAndDrop(originDebit, destinationDebit).build().perform();

        assertEquals(originDebit.getText().trim(), elementText, "User drops debit element");
        assertTrue(driver.findElement(By.xpath("//ol[@id='amt7']/li[@data-id='2']")).isDisplayed(), "Debit value displayed in a table");
    }

    @And("user drop credit account {string}")
    public void userDropCreditAccount(String elementText) {
        WebElement originSalesElement = driver.findElement(By.id("credit1"));
        WebElement destinationSalesElement = driver.findElement(By.id("loan"));

        Actions action = new Actions(driver);
        action.dragAndDrop(originSalesElement, destinationSalesElement).build().perform();

        assertEquals(originSalesElement.getText().trim(), elementText, "User drops SALES element");
        assertTrue(driver.findElement(By.xpath("//ol[@id='loan']/li[@data-id='6']")).isDisplayed(), "Sales element is displayed in a table");
    }

    @And("user drop credit amount {string}")
    public void userDropCreditAmount(String elementText) {
        WebElement originCreditElement = driver.findElement(By.cssSelector("#fourth:nth-child(4)"));
        WebElement destinationCreditElement = driver.findElement(By.id("amt8"));

        Actions action = new Actions(driver);
        action.dragAndDrop(originCreditElement, destinationCreditElement).build().perform();

        assertEquals(originCreditElement.getText().trim(), elementText, "User drops credit element");
        assertTrue(driver.findElement(By.xpath("//ol[@id='amt8']/li[@data-id='2']")).isDisplayed(), "Credit value displayed in a table");
    }

    @Then("success message is shown")
    public void successMessageIsShown() {
        assertEquals(driver.findElement(By.xpath("(//a[@class='button button-green'])[1]")).getText(), "Perfect!", "Checking if Perfect! message appears");
    }

    @After
    public void actionsAfter() {
        driver.quit();
    }
}
