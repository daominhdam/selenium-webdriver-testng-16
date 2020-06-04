package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Technical {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void TC_01_LoginWithEmptyEmailAndPassword() {
		// Mở ra trang Home
		driver.get("http://live.demoguru99.com/index.php/");

		// Click vào My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");

		driver.findElement(By.name("send")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
	}

	public void TC_02_LoginWithInvalidEmail() {
		// Mở ra trang Home
		driver.get("http://live.demoguru99.com/index.php/");

		// Click vào My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("123@123.4565");
		driver.findElement(By.id("pass")).sendKeys("123123123");

		driver.findElement(By.name("send")).click();

		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	public void TC_03_LoginWithInvalidPassword() {

	}

	public void TC_04_LoginWithIncorrectPassword() {
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}
	
	@Test
	public void TC_06_RegisterToSystem() {
		// Mở ra Url
		driver.get("http://live.demoguru99.com/index.php/");
		
		// Click vào My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		// Click vào Create an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Nhập dữ liệu vào các field bắt buộc: Name/ Email/ Password
		driver.findElement(By.name("firstname")).sendKeys("John");
		driver.findElement(By.id("lastname")).sendKeys("Kennedy");
		
		String email = "johnken" + randomNumber() +"@gmail.com";
		driver.findElement(By.className("validate-email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.name("confirmation")).sendKeys("123456");
		
		// Click vào Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
		
		// Name
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'John Kennedy')]")).isDisplayed());
		
		// Email
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'"+ email +"')]")).isDisplayed());
		
		// Click Account
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		
		// Click Logout
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		// Chờ cho element nào đó ở trên Home Page hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}
	
	@Test
	public void TC_07_RegisterToSystem() {
		// Mở ra Url
		driver.get("http://live.demoguru99.com/index.php/");
		
		// Click vào My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		// Click vào Create an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Nhập dữ liệu vào các field bắt buộc: Name/ Email/ Password
		driver.findElement(By.name("firstname")).sendKeys("John");
		driver.findElement(By.id("lastname")).sendKeys("Kennedy");
		
		String email = "johnken" + randomNumber() +"@gmail.com";
		driver.findElement(By.className("validate-email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.name("confirmation")).sendKeys("123456");
		
		// Click vào Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
		
		// Name
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'John Kennedy')]")).isDisplayed());
		
		// Email
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'"+ email +"')]")).isDisplayed());
		
		// Click Account
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		
		// Click Logout
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		// Chờ cho element nào đó ở trên Home Page hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
