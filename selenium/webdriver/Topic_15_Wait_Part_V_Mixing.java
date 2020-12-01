package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_V_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	public void TC_01_Element_Found() {
		driver.get("http://facebook.com");

		// explicit wait
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("automation@gmail.com");

		// implicit wait
		driver.findElement(By.name("login")).click();
	}

	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.get("http://facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		System.out.println(" TC_02_Element_Not_Found_Only_Implicit: " + getDateTimeSecondNow() + " ------ ");
		try {
			WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='automation_testing']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
			System.out.println("Switch to try");
		} catch (Exception ex) {
			System.out.println("TC_02_Element_Not_Found_Only_Implicit:" + ex.getMessage());
			System.out.println("------------------ Exception của implicit --------------");
		}
		System.out.println("TC_02_Element_Not_Found_Only_Implicit: " + getDateTimeSecondNow() + " ------ ");
	}

	public void TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit() {
		driver.get("http://facebook.com/");

		// Apply explicit (Tham số là By) - Implicit > Explicit time
		// Timeout để tìm element là 10s (step)
		// Timeout trong exception là 5s
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		System.out.println("TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit: " + getDateTimeSecondNow() + " ---- ");
		try {
			// Tham số của hàm wait trong explicit là By
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='djfkdsjfkldsjf']")));
			System.out.println("Switch to try");
		} catch (Exception ex) {
			System.out.println("TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit: " + ex.getMessage());
			System.out.println("------------------ Exception của explicit --------------");
		}
		System.out.println("TC_03_Element_Not_Found_Implicit_Greater_Than_Explicit: " + getDateTimeSecondNow() + " ---- ");
	}

	@Test
	public void TC_05_Element_Not_Found_Only_Explicit_By() {
		driver.get("http://facebook.com/");
		// Apply explicit (Tham số là WebElement/ List <WebElement>
		// Timeout của cả step (tìm element) và trong exception đều là 10s
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait = new WebDriverWait(driver, 5);

		System.out.println("TC_05_Element_Not_Found_Only_Explicit_By: " + getDateTimeSecondNow() + " --------- ");
		try {
			// Tham số của hàm wait trong explicit là WebElement
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='tao_ko_co_o_day']")));

			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='tao_ko_co_o_day']")));

			System.out.println("Switch to try");
		} catch (Exception ex) {
			System.out.println("TC_05_Element_Not_Found_Only_Explicit_By: " + ex.getMessage());
			System.out.println("------------------ Exception của explicit --------------");
		}
		System.out.println("TC_05_Element_Not_Found_Only_Explicit_By:" + getDateTimeSecondNow() + " --------- ");
	}

	public void TC_06_Element_Not_Found_Only_Explicit_WebElement() {
		driver.get("http://facebook.com/");

		explicitWait = new WebDriverWait(driver, 10);
		System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement: " + getDateTimeSecondNow() + " --------- ");
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='tao_ko_co_o_day']"))));
			System.out.println("Switch to try");
		} catch (Exception ex) {
			System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement: " + ex.getMessage());
			System.out.println("------------------ Exception của explicit --------------");
		}
		System.out.println("TC_06_Element_Not_Found_Only_Explicit_WebElement: " + getDateTimeSecondNow() + " --------- ");
	}

	public String getDateTimeSecondNow() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}