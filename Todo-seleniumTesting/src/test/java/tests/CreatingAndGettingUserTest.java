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

public class CreatingAndGettingUserTest {
	
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
	
	//#firstName
	@Test
	public void createUsersTest() {
		//creating the first name
		WebElement firstNameContainer = driver.findElement(By.cssSelector("#firstName"));
		Actions action = new Actions(driver);
		action.moveToElement(firstNameContainer).perform();
		action.click();
		action.click().perform();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		firstNameContainer.sendKeys("Leroy");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// creating the last name
		WebElement lastNameContainer = driver.findElement(By.cssSelector("#lastName"));
		action.moveToElement(lastNameContainer).perform();
		action.click();
		action.click().perform();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		lastNameContainer.sendKeys("Jenkins");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//creating the userName
		WebElement userNameContainer = driver.findElement(By.cssSelector("#userName"));
		action.moveToElement(userNameContainer).perform();
		action.click();
		action.click().perform();
		
		userNameContainer.sendKeys("LEEEEEEAAAAARRRROOOOYYYY JENKINSSS!!!");
		
		//creating the email
		WebElement emailContainerElement = driver.findElement(By.cssSelector("#email"));
		action.moveToElement(emailContainerElement).perform();
		action.click();
		action.click().perform();
		emailContainerElement.sendKeys("lrj@email.com");
		
		//creating the password
		WebElement passwordContainerElement = driver.findElement(By.cssSelector("#password"));
		action.moveToElement(passwordContainerElement);
		action.click();
		action.click().perform();
		passwordContainerElement.sendKeys("password");
		
		//clicking create user
		WebElement createUserButtonElement = driver.findElement(By.cssSelector("#createUser"));
		action.moveToElement(createUserButtonElement);
		action.click().perform();
		
		WebElement getUserContainer = driver.findElement(By.cssSelector("#fetchUsers"));
		action.moveToElement(getUserContainer).perform();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		action.click().perform();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	

	
	@AfterClass
	public static void tearDown() {
		driver.quit();
		
	}

}
