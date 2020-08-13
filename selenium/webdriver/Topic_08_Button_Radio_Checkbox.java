package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	// Checkbox
	By firstCheckbox = By.xpath("//input[@value='Anemia']");
	By secondCheckbox = By.xpath("//input[@value='Asthma']");
	By thirdCheckbox = By.xpath("//input[@value='Arthritis']");
	By allCheckboxes = By.xpath("//input[@type='checkbox']");

	// Radio
	By firstRadio = By.xpath("//input[@value='3-4 days']");
	By secondRadio = By.xpath("//input[@value='I have a strict diet']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Button() throws InterruptedException {
		driver.get("https://www.fahasa.com/customer/account/create");

		// Navigate to Login tab
		driver.findElement(By.cssSelector("li.popup-login-tab")).click();

		WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));

		// Verify login button is disabled
		boolean status = loginButton.isEnabled();
		System.out.println("Login status = " + status);
		Assert.assertFalse(status);

		// Input to email/ password
		driver.findElement(By.cssSelector("#login_username")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("132456");
		sleepInSecond(2);

		// Verify login button is enabled
		status = loginButton.isEnabled();
		System.out.println("Login status = " + status);
		Assert.assertTrue(status);

		// Click to Login button
		loginButton.click();

		String errorMessage = driver.findElement(By.cssSelector(".fhs-login-msg")).getText();
		Assert.assertEquals(errorMessage, "Số điện thoại/Email hoặc Mật khẩu sai!");

		driver.navigate().refresh();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("li.popup-login-tab")).click();

		sleepInSecond(5);

		loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));

		// Verify login button is disabled
		Assert.assertFalse(loginButton.isEnabled());

		// Hạn chế sử dụng (Trick) - User ko dùng cách này để thao tác vs app
		// Auto -> giả lập các flow/ hành vi của End User
		removeDisabledAttribute(loginButton);
		sleepInSecond(2);

		loginButton.click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");

	}

	public void TC_02_Default_Radio_Checkbox() throws InterruptedException {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Verify 3 first checkboxes + 2 radio are deselected
		// Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
		// Assert.assertFalse(driver.findElement(secondCheckbox).isSelected());
		// Assert.assertFalse(driver.findElement(thirdCheckbox).isSelected());
		// Assert.assertFalse(driver.findElement(firstRadio).isSelected());
		// Assert.assertFalse(driver.findElement(secondRadio).isSelected());

		// Click to 3 first checkboxes + 2 radio
		// driver.findElement(firstCheckbox).click();
		// driver.findElement(secondCheckbox).click();
		// driver.findElement(thirdCheckbox).click();
		// driver.findElement(firstRadio).click();
		// driver.findElement(secondRadio).click();
		//
		// sleepInSecond(5);

		// Verify 3 first checkboxes + 2 radio are selected
		// Assert.assertTrue(driver.findElement(firstCheckbox).isSelected());
		// Assert.assertTrue(driver.findElement(secondCheckbox).isSelected());
		// Assert.assertTrue(driver.findElement(thirdCheckbox).isSelected());
		// Assert.assertTrue(driver.findElement(firstRadio).isSelected());
		// Assert.assertTrue(driver.findElement(secondRadio).isSelected());

		// driver.navigate().refresh();

		// Click to all checkboxes
		List<WebElement> checkboxes = driver.findElements(allCheckboxes);

		// Select (Checkbox/ Radio)
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			Thread.sleep(500);
		}

		// Verify selected
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}

		// De-select (Checkbox)
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			Thread.sleep(500);
		}

		// Verify deselected
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
		}

	}

	@Test
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");

		// Click by input
		clickByJavascript(driver.findElement(checkedCheckbox));
		sleepInSecond(5);
		
		// Verify Checked checkbox is selected
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void removeDisabledAttribute(WebElement element) {
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", element);
	}

	public void clickByJavascript(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}