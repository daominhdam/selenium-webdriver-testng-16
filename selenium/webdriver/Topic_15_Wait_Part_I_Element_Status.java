package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();
	}

	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");

		// Wait for Email visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='websubmit']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@id='content']//img[contains(@src,'.png')]")));

		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("dam@gmail.com");
	}

	public void TC_02_Invisible() {
		driver.navigate().refresh();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));

		driver.get("http://live.demoguru99.com/index.php");

		// 2.1 - Element có trong DOM + ko hiển thị trên UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		// 2.2 - Element ko có trong DOM + ko hiển thị trên UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));

	}

	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");

		// 3.1 - Element có trong DOM + hiển thị trên UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='websubmit']")));

		driver.get("http://live.demoguru99.com/index.php");

		// 3.2 - Element có trong DOM + ko hiển thị trên UI
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
		
		// Page có status là A
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='create_account_error']//li")));
		WebElement emailErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']//li"));
		
		driver.navigate().refresh();
		
		// Page có status là B
		
		// emailErrorMessage bị staleness
		
		// 4 - Element ko có trong DOM (bị staleness)
		explicitWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));
		
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}