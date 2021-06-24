import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //tags = {"@Home"},
        features = "src/test/resources",
        glue = "cucumber_step_defs",
        plugin = {"pretty", "html:target/htmlreports"}
)

public class TestRunner {

}
