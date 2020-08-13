package webdriver;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Wait_Part_III_Static {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_10S() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// Chỉ cần 3s để cái kết quả xuất hiện

		// Dư thời gian
		sleepInSecond(10);

		// Lãng phí mất 7s

		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='10']")).isDisplayed());
	}

	@Test
	public void TC_02_3S() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// Chỉ cần 3s để cái kết quả xuất hiện

		// Đủ thời gian
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='10']")).isDisplayed());
	}

	@Test
	public void TC_03_1S() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		// Chỉ cần 3s để cái kết quả xuất hiện

		// Thiếu thời gian
		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='10']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}