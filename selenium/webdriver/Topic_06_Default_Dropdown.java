package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_06_Default_Dropdown {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Single() {
		driver.get("https://www.facebook.com/");

		// Có cái dropdown xuất hiện thì mới khởi tạo
		// Khởi tạo 1 biến select: đi tìm element có id bằng day
		select = new Select(driver.findElement(By.id("month")));

		// Dropdown ko hỗ trợ chọn nhiều (multiple)
		boolean status = select.isMultiple();
		System.out.println(status);
		Assert.assertFalse(status);

		// ko nên dùng
		select.selectByIndex(2);

		// Kiểm tra đã chọn 1 item thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 2");

		// value (String)
		select.selectByValue("5");

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 5");

		// visible text
		select.selectByVisibleText("Tháng 10");

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 10");

		// Get ra tất cả các thẻ option của dropdown (item)
		List<WebElement> monthOption = select.getOptions();

		// In ra xem có bao nhiêu giá trị
		int monthOptionSize = monthOption.size();
		System.out.println("Month item number = " + monthOptionSize);

		// Kiểm tra số lượng item của dropdown bằng bao nhiêu
		Assert.assertEquals(monthOptionSize, 13);

		// Kiểm tra được dropdown nó hiển thị đúng các giá trị (Requirement)
		ArrayList<String> actualItem = new ArrayList<String>();
		actualItem.add("Tháng");
		actualItem.add("Tháng 1");
		actualItem.add("Tháng 2");
		actualItem.add("Tháng 3");
		actualItem.add("Tháng 4");
		actualItem.add("Tháng 5");
		actualItem.add("Tháng 6");
		actualItem.add("Tháng 7");
		actualItem.add("Tháng 8");
		actualItem.add("Tháng 9");
		actualItem.add("Tháng 10");
		actualItem.add("Tháng 11");
		actualItem.add("Tháng 12");

		ArrayList<String> expectedItem = new ArrayList<String>();

		// for-each
		for (WebElement option : monthOption) {
			expectedItem.add(option.getText());
		}

		Assert.assertEquals(expectedItem, actualItem);

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());

	}

	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));

		// Hỗ trợ multiple select
		Assert.assertTrue(select.isMultiple());

		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Perfomance");

		int optionSelected = select.getAllSelectedOptions().size();
		System.out.println("Item selected = " + optionSelected);
		Assert.assertEquals(optionSelected, 3);

		List<WebElement> optionSelectedElement = select.getAllSelectedOptions();
		for (WebElement option : optionSelectedElement) {
			System.out.println(option.getText());
		}

		// Bỏ chọn hết cả 3 thằng
		select.deselectAll();

		optionSelected = select.getAllSelectedOptions().size();
		System.out.println("Item selected = " + optionSelected);
		Assert.assertEquals(optionSelected, 0);
	}

	public void sleepInSecond(long time) {
		// 1000 ms = 1s
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}