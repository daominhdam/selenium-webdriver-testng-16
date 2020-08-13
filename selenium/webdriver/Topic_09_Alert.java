package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_BankGuru() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4/index.php");

		// Click Login
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(2);

		// Switch vào Alert
		alert = driver.switchTo().alert();
		sleepInSecond(2);

		// Get text của Alert
		System.out.println("Alert text = " + alert.getText());

		// Send text vào Alert (Prompt)
		// alert.sendKeys("");

		// Accept alert
		alert.accept();
		sleepInSecond(2);

		// Cancel alert
		// alert.dismiss();

	}

	public void TC_02_JS_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		/*---------- 1 - Alert Accept -------------*/
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// Switch to Alert
		alert = driver.switchTo().alert();

		// Verify Alert text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept Alert
		alert.accept();
		sleepInSecond(3);

		// Verify accept alert success
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");

		/*---------- 2 - Alert Confirm -------------*/
		driver.navigate().refresh();

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Switch to Alert
		alert = driver.switchTo().alert();

		// Verify Alert text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Cancel Alert
		alert.dismiss();
		sleepInSecond(3);

		// Verify dismiss alert success
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

		/*---------- 3 - Alert Prompt -------------*/
		driver.navigate().refresh();

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		// Switch to Alert
		alert = driver.switchTo().alert();

		// Verify Alert text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// Sendkey to Alert
		alert.sendKeys("Automation FC");
		sleepInSecond(3);

		// Accept Alert
		alert.accept();

		// Verify accept alert success
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Automation FC");
	}

	public void TC_03_Authentication_Alert() {
		String username = "admin";
		String password = "admin";

		// Xác thực qua link luôn - ko bật Alert lên nữa
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");

		// By pass Authentication Alert
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
	}

	public void TC_04_Authentication_Alert() {
		String username = "admin";
		String password = "admin";

		driver.get("http://the-internet.herokuapp.com/");

		// Get link href
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		handleAuthenticationAlert(basicAuthenLink, username, password);

		// By pass Authentication Alert
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
	}

	@Test
	public void TC_05_Authentication_Alert_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		String rootFolder = System.getProperty("user.dir");
		String firefoxAuthen = rootFolder + "\\autoITScript\\authen_firefox.exe";
		String chromeAuthen = rootFolder + "\\autoITScript\\authen_chrome.exe";
		String authenUrl = "http://the-internet.herokuapp.com/basic_auth";

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxAuthen, username, password });
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { chromeAuthen, username, password });
		}

		driver.get(authenUrl);

		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
	}

	public void handleAuthenticationAlert(String link, String username, String password) {
		String splitLink[] = link.split("//");
		link = splitLink[0] + "//" + username + ":" + password + "@" + splitLink[1];
		driver.get(link);
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}