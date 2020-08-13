package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Element {
	WebDriver driver;
	
	// Ko hề đi tìm element/ và cũng ko dùng biến driver
	By emailBy = By.id("email");
	By ageUnderBy = By.id("under_18");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// Khởi tạo xong mới có giá trị

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(enabled = false)
	public void TC_01_Web_Browser_Check_Url() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test(enabled = false)
	public void TC_02_Web_Browser_Check_Title() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		Assert.assertEquals(driver.getTitle(), "Customer Login");

		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test(enabled = false)
	public void TC_03_Web_Browser_Navigate() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test(enabled = true)
	public void TC_04_Web_Browser_Page_Source() {
		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));

		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}

	@Test
	public void TC_05_Web_Element() throws InterruptedException {
		driver.get("https://www.facebook.com/");

		WebElement element = driver.findElement(By.xpath("//input[@name='lastname']"));

		// Xóa dữ liệu trong 1 cái textbox/ textarea/ dropdown (editable)
		element.clear();

		// Nhập liệu vào các field có thể nhập được: textbox/ textarea/ dropdown
		// (editable)
		element.sendKeys("Automation FC");
		Thread.sleep(3000);

		// Lấy ra giá rị của 1 cái attribute thuộc về element đang thao tác
		String lastNamePlaceholderText = element.getAttribute("aria-label");
		System.out.println(lastNamePlaceholderText);
		Thread.sleep(3000);

		// Click vào 1 button/ radio/ checkbox/ link/ image/...
		element = driver.findElement(By.xpath("//label[@id='loginbutton']"));
		element.click();
		Thread.sleep(3000);

		element = driver.findElement(By.id("loginbutton"));
		String backgroundColorValue = element.getCssValue("background-color");
		String fontSize = element.getCssValue("font-size");
		// Mã màu của button = #4267b2 (hexa) | rgb (black/ yellow/ white/...)
		Thread.sleep(3000);
		System.out.println("Mã màu = " + backgroundColorValue);
		System.out.println("Font size = " + fontSize);

		element = driver.findElement(By.cssSelector("#email"));
		System.out.println("Tagname = " + element.getTagName());
		Thread.sleep(3000);
		// input

		element = driver.findElement(By.xpath("//div[@id='header_block']/span"));
		System.out.println("Header text = " + element.getText());
		// Đăng nhập Facebook
		Thread.sleep(3000);

		element = driver.findElement(By.xpath("//*[@id='login_link']//a"));
		System.out.println("Link text = " + element.getText());
		// Quên tài khoản?
		Thread.sleep(3000);

		// Kiểm tra 1 element có hiển thị ở trên UI hay ko
		// true/ false
		Assert.assertTrue(element.isDisplayed());

		// Kiểm tra 1 element có phải là bị disabled hay ko
		// true - đang enable
		// false - bị disabled
		Assert.assertTrue(element.isEnabled());

		// Kiểm tra 1 element có thể được chọn hay chưa: radio button/ checkbox/
		// dropdown
		// Assert.assertTrue(element.isSelected());
		// true - được chọn rồi
		// false - chưa được chọn

		// tagname: form
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element.submit();
		Thread.sleep(3000);
	}

	@Test
	public void TC_06_Web_Element_Displayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Email textbox
		if (isElementDisplayed("//input[@id='mail']")) {
			driver.findElement(By.id("mail")).sendKeys("Automation Testing");
			Thread.sleep(2000);
		}

		// Education textarea
		if (isElementDisplayed("//textarea[@id='edu']")) {
			driver.findElement(By.id("edu")).sendKeys("Automation Testing");
			Thread.sleep(2000);
		}

		// Age Under 18 - Radio Button
		if (isElementDisplayed("//input[@id='under_18']")) {
			driver.findElement(By.id("under_18")).click();
			Thread.sleep(2000);
		}
	}

	@Test
	public void TC_07_Web_Element_Enabled() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(isElementEnabled("//input[@id='mail']"));
		Assert.assertTrue(isElementEnabled("//textarea[@id='edu']"));
		Assert.assertFalse(isElementEnabled("//input[@id='slider-2']"));
	}

	@Test
	public void TC_08_Web_Element_Selected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Over 18 radio
		driver.findElement(By.id("over_18")).click();

		// Development Checkbox
		driver.findElement(By.id("development")).click();

		Assert.assertTrue(isElementSelected("//input[@id='over_18']"));
		Assert.assertTrue(isElementSelected("//input[@id='development']"));

		// Over 18 radio
		driver.findElement(By.id("over_18")).click();

		// Development Checkbox (Click lần nữa = bỏ chọn)
		driver.findElement(By.id("development")).click();

		Assert.assertTrue(isElementSelected("//input[@id='over_18']"));
		Assert.assertFalse(isElementSelected("//input[@id='development']"));
	}

	public boolean isElementDisplayed(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isDisplayed()) {
			System.out.println("Element with xpath = " + xpathValue + " is displayed!");
			return true;
		} else {
			System.out.println("Element with xpath = " + xpathValue + " is not displayed!");
			return false;
		}
	}

	public boolean isElementEnabled(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isEnabled()) {
			System.out.println("Element with xpath = " + xpathValue + " is enabled!");
			return true;
		} else {
			System.out.println("Element with xpath = " + xpathValue + " is disabled!");
			return false;
		}
	}

	public boolean isElementSelected(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isSelected()) {
			System.out.println("Element with xpath = " + xpathValue + " is selected!");
			return true;
		} else {
			System.out.println("Element with xpath = " + xpathValue + " is deselected!");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}