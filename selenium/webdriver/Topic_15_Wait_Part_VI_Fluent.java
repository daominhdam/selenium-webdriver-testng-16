package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_Wait_Part_VI_Fluent {
	WebDriver driver;
	WebElement element;
	FluentWait<WebElement> fluentElement;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_() {
		FluentWait<WebElement> fluentElement;
		
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));

		fluentElement = new FluentWait<WebElement>(countdount);

		fluentElement.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class).until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {
						boolean flag = element.getText().endsWith("00");
						return flag;
					}
				});
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}