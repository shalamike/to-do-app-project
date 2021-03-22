package tests;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import pages.UserPage;

public class GoingToTodoTest {
	private static WebDriver driver;
	
	@BeforeClass
	public static void init() {
		System.setProperty("webdriver.gecko.driver", 
				"src/test/resources/drivers/geckodriver.exe");
		FirefoxOptions fOptions = new FirefoxOptions();
		fOptions.setHeadless(false);
		fOptions.addPreference("profile.default_content_setting_values.cookies", 2); 
		fOptions.addPreference("network.cookie.cookieBehavior", 2); 
		fOptions.addPreference("profile.block_third_party_cookies", true);
		
		driver = new FirefoxDriver(fOptions);
	}
	
	@Before
	public void setup() {
		driver.manage().timeouts().pageLoadTimeout(4, TimeUnit.SECONDS);
		driver.get(UserPage.URL);
	}
	
	@Test
	public void basicTest() {
		System.out.println(driver.getTitle());
	}
	
	@Test
	public void goToTodosTest() {
		WebElement GetTodosElement = driver.findElement(By.cssSelector("#todoButton"));
		Actions action = new Actions(driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.moveToElement(GetTodosElement).perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDown() {
		driver.quit();
		
	}
}
