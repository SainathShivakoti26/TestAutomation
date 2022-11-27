package automationbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

public class AutomationBase {

	public static WebDriver driver;
	public static WebDriverWait webDriverWait;
	public static Properties properties = new Properties();
	public static FileReader fileReader;

	public static String chromeDriverPath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
	public static String firefoxDriverPath = System.getProperty("user.dir") + "/drivers/geckodriver.exe";
	public static String msedgeDriverPath = System.getProperty("user.dir") + "/drivers/msedgedriver.exe";

	@BeforeTest
	public static WebDriver setUp() throws Exception {
		if (driver == null) {
			fileReader = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\configfiles\\configuration.properties");
			properties.load(fileReader);
		}

		String browser = properties.getProperty("browser").toLowerCase();
		String appURL = properties.getProperty("testURL");

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "msedge":
			System.setProperty("webdriver.edge.driver", msedgeDriverPath);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + browser);
		}

		System.out.println(driver);

		webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(appURL);
		driver.manage().window().maximize();
		webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("logo"))));
		return driver;
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
		System.out.println("TearDown Successful...");
	}
}
