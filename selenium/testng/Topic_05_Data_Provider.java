package testng;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_05_Data_Provider {

	WebDriver driver;
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// 1-2-3
	@Test(dataProvider = "register_login")
	public void TC_01_Register_To_System(String username, String password) {
		System.out.println(username);
		System.out.println(password);
	}
	
	// 1-2-3
	@Test(dataProvider = "register_login")
	public void TC_02_Login_To_System(String username, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();

		// ...

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData() {
		return new Object[][] { { "selenium_11_01@gmail.com", "111111" }, // 1
				{ "selenium_11_02@gmail.com", "111111" }, // 2
				{ "selenium_11_03@gmail.com", "111111" } }; // 3
	}

	@DataProvider(name = "register_login")
	public Object[][] registerAndLogin(Method methodName) {
		Object[][] result = null;
		if (methodName.getName().contains("Login")) {
			result = new Object[][] { 
					{ "selenium_11_01@gmail.com", "111111" }, 
					{ "selenium_11_02@gmail.com", "111111" }, 
					{ "selenium_11_03@gmail.com", "111111" } };
		} else if(methodName.getName().contains("Register")) {
			result = new Object[][] { 
					{ "selenium_11_01", "000000" }, 
					{ "selenium_11_02", "000000" }, 
					{ "selenium_11_03", "000000" } };
		}
		return result;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
