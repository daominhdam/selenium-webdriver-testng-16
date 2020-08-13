package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Custom_Dropdown {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// Explicit
		explicitWait = new WebDriverWait(driver, 30);

		jsExecutor = (JavascriptExecutor) driver;

		// Implicit
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// Action: select item
		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "5");
		// Verify: verify item selected
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "10");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());

		selectItemInDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "6");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());
	}

	public void TC_02_Angular() {
		driver.get("https://bit.ly/2UV2vYi");

		selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "Basketball");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Basketball");

		selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Golf");

		selectItemInDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']/li", "American Football");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "American Football");
	}

	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdown("//i[@class='dropdown icon']", "//span[@class='text']", "Justen Kitsune");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='text' and text()='Justen Kitsune']")).isDisplayed());

		selectItemInDropdown("//i[@class='dropdown icon']", "//span[@class='text']", "Stevie Feliciano");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='text' and text()='Stevie Feliciano']")).isDisplayed());

		selectItemInDropdown("//i[@class='dropdown icon']", "//span[@class='text']", "Jenny Hess");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='text' and text()='Jenny Hess']")).isDisplayed());
	}

	public void TC_04_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		sendkeyToEditDropdown("//div[@id='default-place']/input", "Ford");
		Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Ford");

		sendkeyToEditDropdown("//div[@id='default-place']/input", "Audi");
		Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Audi");

		sendkeyToEditDropdown("//div[@id='default-place']/input", "Land Rover");
		Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Land Rover");
	}

	@Test
	public void TC_05_Advanced() {
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		String[] month = { "January", "April", "July" };
		selectMultiItemInDropdown("//option/parent::select/following-sibling::div", "//option/parent::select/following-sibling::div//label/span", month);
		Assert.assertTrue(areItemSelected(month));

		driver.navigate().refresh();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		String[] months = { "January", "April", "July", "September" };

		selectMultiItemInDropdown("//option/parent::select/following-sibling::div", "//option/parent::select/following-sibling::div//label/span", months);
		Assert.assertTrue(areItemSelected(months));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sendkeyToEditDropdown(String locator, String value) {
		driver.findElement(By.xpath(locator)).clear();
		driver.findElement(By.xpath(locator)).sendKeys(value);
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
		sleepInSecond(1);
	}

	// Hàm này được dùng đi dùng lại nhiều lần (chỉ cần truyền giá trị vào)
	public void selectItemInDropdown(String parentLocator, String itemLocator, String expectedItem) {
		// 1 - Click vào 1 thẻ bất kì để xổ ra hết tất cả các item trong dropdown ra
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);

		// 2 - Chờ cho tất cả item được có trong HTML DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));

		// 3 - Lấy hết tất cả các item này đưa vào 1 List Element
		List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));

		// Tổng số lượng item trong 1 dropdown bằng bao nhiêu
		System.out.println("Item size = " + allItems.size());

		// 4 - Duyệt qua từng cái item
		for (WebElement item : allItems) {
			// 5 - Mỗi lần duyệt kiểm tra xem text của item đó có bằng vs item mình cần click hay ko
			String actualItem = item.getText();
			System.out.println(actualItem);

			// Nếu như mà bằng thì click vào và thoát khỏi ko duyệt nữa
			// Nếu như mà ko bằng thì lại duyệt tiếp cho đến hết tất cả item
			if (actualItem.equals(expectedItem)) {
				// Trước khi click thì nên scroll đến element
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);

				// Wait cho element clickable
				explicitWait.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				sleepInSecond(2);
				break;
			}
		}
	}

	public String getHiddenText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");
	}

	public void selectMultiItemInDropdown(String parentXpath, String allItemXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {

			// "January", "April", "July"
			for (String item : expectedValueItem) {

				if (childElement.getText().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(2);

					// 4: click vào item cần chọn
					jsExecutor.executeScript("arguments[0].click();", childElement);
					// childElement.click();
					sleepInSecond(2);

					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean areItemSelected(String[] itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : itemSelectedText) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		} else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		}
	}
}