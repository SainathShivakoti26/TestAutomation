import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty","json:target/cucumberreports.json" },
        glue = "src/main/resources/stepdefinitions/",
        features = "src/main/resources/featurefiles/",
        tags={""},
        dryRun=false
)
public class RunnerTest {
}
