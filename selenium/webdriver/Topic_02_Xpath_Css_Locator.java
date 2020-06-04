package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	// Khai báo biến driver (instance = thể hiện/ đại diện)
	WebDriver driver;

	@BeforeClass // Pre-condition
	public void beforeClass() {
		// Khởi tạo trình duyệt Firefox lên
		driver = new FirefoxDriver();

		// Phóng to browser lên
		driver.manage().window().maximize();

		// Chờ cho element được stable trước khi thao tác trong vòng xx seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Mở cái app Url
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	// Html của Email textbox
	// <input id="email" class="input-text required-entry validate-email"
	// type="email"
	// title="Email Address" value="" name="login[username]"
	// spellcheck="false" autocorrect="off" autocapitalize="off"/>

	// Trong Selenium có 3 loại locator là 3 dạng attribute của HTML (id/ class/
	// name)
	// Hay được development team set là duy nhất (uniqe)

	@Test // Testcase: Action/ Verify
	public void TC_01_ID() throws InterruptedException {
		// Tương tác vs element
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		Thread.sleep(2000);

		// Xóa dữ liệu trong các element có thể nhập được: textbox/ textarea/
		// dropdown/...
		driver.findElement(By.id("email")).clear();
	}

	@Test
	public void TC_02_Class() throws InterruptedException {
		driver.findElement(By.className("validate-password")).sendKeys("123465");
		Thread.sleep(2000);
		driver.findElement(By.className("validate-password")).clear();
	}

	@Test
	public void TC_03_Name() throws InterruptedException {
		driver.findElement(By.name("login[username]")).sendKeys("name@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.name("login[username]")).clear();
	}

	@Test
	public void TC_04_Tagname() throws InterruptedException {
		int linkNumber = driver.findElements(By.tagName("a")).size();
		System.out.println("Sum link = " + linkNumber);
		// 27
		// Sum link = 27
		Thread.sleep(2000);
	}

	@Test // Nó chỉ work vs những đường link vs text cố định (tuyệt đối)
	public void TC_05_Link_Text() throws InterruptedException {
		// Click vào SITE MAP link
		driver.findElement(By.linkText("SITE MAP")).click();
		Thread.sleep(2000);
	}

	@Test // Nó chỉ work vs những đường link vs text tương đối
	public void TC_06_Partial_Link_Text() throws InterruptedException {
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		Thread.sleep(2000);
	}

	@Test
	public void TC_07_Css() throws InterruptedException {
		// ID
		driver.findElement(By.cssSelector("#name")).sendKeys("LCD");
		Thread.sleep(2000);

		// Class
		driver.findElement(By.cssSelector(".advanced-search")).isDisplayed();

		// Name
		driver.findElement(By.cssSelector("input[name='short_description']")).sendKeys("Samsung LCD");
		Thread.sleep(2000);

		// Link Text
		driver.findElement(
				By.cssSelector("a[href='http://live.demoguru99.com/index.php/catalog/seo_sitemap/category/']")).click();

		// Partial Link
		driver.findElement(By.cssSelector("a[href*='/catalogsearch/advanced/']")).click();

		// Tagname
		int linkSize = driver.findElements(By.cssSelector("a")).size();
		System.out.println("Css Tagname = " + linkSize);
	}

	@Test
	public void TC_08_Xpath() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Thread.sleep(2000);

		// ID
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath_id@gmail.com");
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		Thread.sleep(2000);
		
		// Class
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("xpath_class@gmail.com");
		Thread.sleep(2000);
		
		// Name
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("xpath_name@gmail.com");
		Thread.sleep(2000);
		
		// Link text
		driver.findElement(By.xpath("//a[text()='About Us']")).click();
		Thread.sleep(2000);
		
		// Partial link
		driver.findElement(By.xpath("//a[contains(text(),'Customer')]")).click();
		Thread.sleep(2000);
		
		// Tagname
		System.out.println(driver.findElements(By.xpath("//a")).size());
		Thread.sleep(2000);
		
		// Css
		driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")).sendKeys("xpath_css@gmail.com");
		Thread.sleep(2000);
	}

	@AfterClass // Post-condition
	public void afterClass() {
		driver.quit();
	}

}
