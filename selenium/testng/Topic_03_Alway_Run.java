package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Alway_Run {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.out.println("Pre-Condition 1 - Init browser");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		System.out.println("Pre-Condition 2 - Init Data Test");

		System.out.println("Pre-Condition 3 - Register User");
		Assert.assertTrue(false); // Failed
		
		System.out.println("Pre-Condition 4 - Login User");
	}

	@Test
	public void TC_01() {
		System.out.println("Run Test 01");
	}

	@Test
	public void TC_02() {
		System.out.println("Run Test 02");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
		System.out.println("Run After");
	}

}
