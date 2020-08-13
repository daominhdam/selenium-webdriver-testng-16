package webdriver;

import java.util.Set;

import org.openqa.selenium.Alert;
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

public class Topic_15_Wait_Part_IV_Explicit {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	WebElement dateSelected;
	String today = "Sunday, August 09, 2020";
	String source_folder = System.getProperty("user.dir");
	String image_name_01 = "BPhone.jpg";
	String image_name_02 = "iPhone.jpg";
	String image_name_03 = "Samsung.jpg";

	String image_01_path = source_folder + "\\uploadFiles\\" + image_name_01;
	String image_02_path = source_folder + "\\uploadFiles\\" + image_name_02;
	String image_03_path = source_folder + "\\uploadFiles\\" + image_name_03;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
	}

	public void TC_01_Alert_Presence() {
		driver.get("http://demo.guru99.com/v4/index.php");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.name("btnLogin")));
		driver.findElement(By.name("btnLogin")).click();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		System.out.println(alert.getText());
		alert.accept();
	}

	public void TC_02_Visible() {
		driver.get("http://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("5");
		driver.findElement(By.id("gobutton")).click();

		WebElement resultText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='10']")));

		Assert.assertTrue(resultText.isDisplayed());
	}

	public void TC_03_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button"))).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Chờ cho Date Time Picker được hiển thị (visible/ displayed)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));

		// DOM trước khi Click
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));

		// Ngày đã chọn: No Selected Dates to display.
		Assert.assertEquals(dateSelected.getText(), "No Selected Dates to display.");

		// Click vào ngày hiện tại
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='" + today + "']"))).click();

		// Chờ cho Loading Icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='raDiv']"))));

		// DOM sau khi Click
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));

		// Ngày đã chọn: Sunday, August 09, 2020
		Assert.assertEquals(dateSelected.getText(), "Sunday, August 9, 2020");
	}

	public void TC_05_Upload_File() {
		driver.get("https://gofile.io/uploadFiles");

		// Chờ cho Upload file button visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='btnChooseFiles']")));

		// Lấy ra ID của tab hiện tại
		String parentID = driver.getWindowHandle();

		// Định nghĩa element upload và sendkey vào
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);

		// Chờ cho button Upload xuất hiện để click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#btnUpload"))).click();

		// Chờ cho Inprogress loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progressBarDataPercent")));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Ok']"))).click();

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#link"))).click();

		// Chuyển qua tab mới
		switchToWindowByID(parentID);

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='" + image_name_01 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")));

		// Verify download icon displayed at each file name
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_01 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_02 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_03 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());

		// Verify play icon displayed at each file name
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_01 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_02 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_03 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
	}

	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}