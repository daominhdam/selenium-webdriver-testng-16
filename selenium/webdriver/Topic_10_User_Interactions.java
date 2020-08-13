package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interactions {
	// Khai báo
	WebDriver driver;
	WebElement element;
	Actions action;
	JavascriptExecutor jsExecutor;
	String rootFolder = System.getProperty("user.dir");
	String javascriptPath = rootFolder + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jqueryPath = rootFolder + "\\dragAndDrop\\jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {
		// Nơi nó sinh ra (khởi tạo)
		driver = new FirefoxDriver();

		// Nơi nó được sử dụng (nó chưa sinh ra)
		action = new Actions(driver);

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Hover_Mouse() {
		driver.get("https://www.myntra.com/");

		element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));

		// Hover to KIDS menu
		action.moveToElement(element).perform();

		driver.findElement(By.xpath("//ul[@class='desktop-navBlock']//a[text()='Home & Bath']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	}

	public void TC_02_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		String[] selectedTextExpected = { "1", "2", "3", "4", "5", "6", "7", "8" };

		List<WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));

		// Click chọn từ 1 đến 8
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(7)).release().perform();

		// Verify chọn từ 1 đến 8 thành công
		List<WebElement> allItemsSelected = driver.findElements(By.cssSelector(".ui-selected"));

		// Verify size = 8
		Assert.assertEquals(allItemsSelected.size(), 8);

		// Tạo ra 1 ArrayList để lưu lại selected text
		ArrayList<String> allItemSelectedText = new ArrayList<String>();

		// Verify cái text của các element là từ số 1 đến số 8 ko
		for (WebElement webElement : allItemsSelected) {
			allItemSelectedText.add(webElement.getText());
		}

		Object[] selectedTextActual = (Object[]) allItemSelectedText.toArray();

		Assert.assertEquals(selectedTextExpected, selectedTextActual);
	}

	public void TC_03_Click_And_Hold_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));
		// 1 - Nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();

		// 2 - Click vào các số cần chọn: 1/ 4/ 7/ 12
		action.click(allItems.get(0)).click(allItems.get(3)).click(allItems.get(6)).click(allItems.get(11)).perform();

		// 3 - Nhả phím Ctrl ra
		action.keyUp(Keys.CONTROL).perform();

		sleepInSecond(5);

		// Verify 4 số chọn thành công: 1/ 4/ 7/ 12
		List<WebElement> allItemsSelected = driver.findElements(By.cssSelector(".ui-selected"));
		Assert.assertEquals(allItemsSelected.size(), 4);
	}

	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		element = driver.findElement(By.xpath("//button[text()='Double click me']"));

		action.doubleClick(element);
		action.perform();
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());

	}

	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Right click to 'button'
		element = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(element).perform();

		// Hover to 'Quit'
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		action.moveToElement(element).perform();

		// Verify 'Quit' has: hover/ visible status
		String quitClassAttribute = element.getAttribute("class");
		System.out.println(quitClassAttribute);

		Assert.assertTrue(quitClassAttribute.contains("context-menu-hover"));
		Assert.assertTrue(quitClassAttribute.contains("context-menu-visible"));

		// isDisplayed
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	}

	public void TC_06_Drag_Drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");

		WebElement source = driver.findElement(By.cssSelector("#draggable"));
		WebElement target = driver.findElement(By.cssSelector("#droptarget"));

		// 1
		// action.dragAndDrop(source, target).perform();

		// 2
		action.clickAndHold(source).moveToElement(target).release().perform();
		sleepInSecond(3);

		Assert.assertEquals(target.getText(), "You did great!");
	}

	public void TC_07_Drag_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSecond(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		java_script = "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSecond(5);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
	}

	@Test
	public void TC_08_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(3);

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(3);

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSecond(3);
	}

	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		}
		return false;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 85 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 85 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}