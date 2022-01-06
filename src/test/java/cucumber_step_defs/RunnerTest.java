package cucumber_step_defs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@Smoke, @DragDrop, @Set",           //указав тег, можем тут же запускать через TestRunner
        features = "src/test/resources", // Можно указать несколько папок, например: features = {«src/test/features», «src/test/feat»}
        glue = "cucumber_step_defs",  // Можно указать несколько пакетов, например, так: glue = {«ru.savkk.test», «ru.savkk.hooks»}
        plugin = {"pretty", "html:target/htmlreports"}
)

public class RunnerTest {    //обязательно название класа заканчивается на Test

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void afterScenario() {
        driver.quit();
    }
}