package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Topic_14_Upload_File {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String source_folder = System.getProperty("user.dir");
	String image_name_01 = "BPhone.jpg";
	String image_name_02 = "iPhone.jpg";
	String image_name_03 = "Samsung.jpg";

	String image_01_path = source_folder + "\\uploadFiles\\" + image_name_01;
	String image_02_path = source_folder + "\\uploadFiles\\" + image_name_02;
	String image_03_path = source_folder + "\\uploadFiles\\" + image_name_03;

	String chrome_auto_it = source_folder + "\\autoITScript\\chromeUploadOneTime.exe";
	String chrome_auto_it_multiple = source_folder + "\\autoITScript\\chromeUploadMultiple.exe";

	public void TC_01_Sendkey_Firefox() {
		System.setProperty("webdriver.gecko.driver", source_folder + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);
		sleepInSecond(1);

		// Before upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_name_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_name_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_name_03 + "']")).isDisplayed());

		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_name_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_name_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_name_03 + "']")).isDisplayed());

		// After upload
		sleepInSecond(3);
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_name_01 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_name_02 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_name_03 + "')]"));
	}

	public void TC_02_Sendkey_Chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);
		sleepInSecond(1);

		// Before upload
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_name_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_name_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_name_03 + "']")).isDisplayed());

		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_name_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_name_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_name_03 + "']")).isDisplayed());

		// After upload
		sleepInSecond(3);
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_name_01 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_name_02 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_name_03 + "')]"));
	}

	public void TC_03_Sendkey_GoFile() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=vi");
		ChromeDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://gofile.io/uploadFiles");

		String parentID = driver.getWindowHandle();

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);
		sleepInSecond(1);

		driver.findElement(By.cssSelector("button#btnUpload")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("button[aria-label='Ok']")).isDisplayed());

		driver.findElement(By.cssSelector("button[aria-label='Ok']")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("a#link")).isDisplayed());
		driver.findElement(By.cssSelector("a#link")).click();

		switchToWindowByID(parentID);

		// Verify download icon displayed at each file name
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_01 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_02 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_03 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());

		// Verify play icon displayed at each file name
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_01 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_02 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_name_03 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
	}

	public void TC_04_AutoIT() throws IOException {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector(".fileinput-button")).click();

		sleepInSecond(3);

		// Execute 1 execution file (.exe/ .bat/..)
		Runtime.getRuntime().exec(new String[] { chrome_auto_it_multiple, image_01_path, image_02_path });
	}
	
	@Test
	public void TC_05_Java_Robot() throws IOException, AWTException {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector(".fileinput-button")).click();

		sleepInSecond(2);
		
		 // Specify the file location with extension
        StringSelection select = new  StringSelection(image_01_path);

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        Robot robot = new Robot();
        sleepInSecond(1);

        // Nhan phim Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Nhan xuong Ctrl - V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Nha Ctrl - V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        sleepInSecond(1);

        // Nhan Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

	}

	@AfterMethod
	public void afterMethod() {
		// driver.quit();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isImageDisplayed(String xpathLocator) {
		jsExecutor = (JavascriptExecutor) driver;
		Boolean imagePresence = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth " + "!= \"undefined\" && arguments[0].naturalWidth > 0", driver.findElement(By.xpath(xpathLocator)));
		return imagePresence;
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

}