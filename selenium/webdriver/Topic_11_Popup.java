package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	boolean status;

	public void TC_01_Popup_Fix() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://zingpoll.com/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("Loginform")));
		sleepInSecond(3);
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(3);

		// Login popup hiển thị
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login popup hiển thị = " + status);
		Assert.assertTrue(status);

		// Click để close cái popup đi
		driver.findElement(By.cssSelector("#Login .close")).click();
		sleepInSecond(3);

		// Login popup ko hiển thị
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login popup ko hiển thị = " + status);
		Assert.assertFalse(status);

		sleepInSecond(2);
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(2);

		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		sleepInSecond(1);
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		driver.findElement(By.id("button-login")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username' and contains(text(),'Automation Testing')]")).isDisplayed());
		driver.quit();
	}

	@Test
	public void TC_02_Popup_Random_Displayed_Undisplayed() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blog.testproject.io/");

		// Step 01
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			System.out.println("-------------- Đi vào trong hàm If --------------");
			// Kiểm tra "SIGN UP NOW" button hiển thị
			Assert.assertTrue(driver.findElement(By.cssSelector(".right-arr.lazyloaded")).isDisplayed());

			// Close popup đi
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(10);
		}

		// Step 02
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");

		// Step 03 - Click search icon
		driver.findElement(By.cssSelector("#search-2 .glass")).click();

		// Step 04 - Verify 'Selenium' in all article title on first page
		List<WebElement> allArticleTitle = driver.findElements(By.cssSelector(".post-title"));

		// Step 05 - Verify 8 articles
		for (WebElement article : allArticleTitle) {
			String articleText = article.getText().trim();
			Assert.assertTrue(articleText.contains("Selenium"));
		}

		driver.quit();

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}