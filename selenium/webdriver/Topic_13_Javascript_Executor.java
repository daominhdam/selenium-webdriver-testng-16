package webdriver;

import java.util.Random;
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

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	String name, dateOfBirth, address, city, state, pin, phone, email, gender, password;
	
	By customerNameTextbox = By.name("name");
	By genderMaleRadioButton = By.xpath("//input[@value='m']");
	By genderTextbox = By.name("gender");
	By dateOfBirthTextbox = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;
		
		name = "John Terry";
		gender = "male";
		dateOfBirth = "1986-01-01";
		address = "105 Le Lai\nHai Chau\nDa Nang";
		city = "Los Angeles";
		state = "Califonia";
		pin = "326542";
		phone = "0987655333";
		email = "johnterry" + randomNumber() + "@hotmail.com";
		password = "123456789";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Click_Hidden_Element() throws InterruptedException {
		driver.get("https://www.myntra.com/");

		WebElement homeAndPathLink = driver.findElement(By.xpath("//a[text()='Home & Bath']"));

		jsExecutor.executeScript("arguments[0].click();", homeAndPathLink);

		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
	}

	@Test
	public void TC_02_Live_Guru() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");

		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");

		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		String liveGuruInnerValue = getPageInnnerText();
		Assert.assertTrue(liveGuruInnerValue.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");

		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");

		Assert.assertTrue(verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String demoGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}

	public void TC_03_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4/");

		driver.findElement(By.name("uid")).sendKeys("mngr270179");
		driver.findElement(By.name("password")).sendKeys("qyredAt");
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");

		// Click to New Customer link
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input to Required (Mandantory) Fields
		driver.findElement(customerNameTextbox).sendKeys(name);
		
		// Remove attribute type=date
		removeAttributeInDOM("//input[@name='dob']", "type");
		sleepInSecond(5);
		
		// 'Date of birth' Date Time Picker
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		
		
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);

		// Click to Submit button
		driver.findElement(By.name("sub")).click();

		// Verify create New Customer success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");

		// Verify input data (User) matching with output data (Server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Browser
	public Object executeForBrowser(String javascript) {
		return jsExecutor.executeScript(javascript);
	}

	public String getPageInnnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location='" + url + "'");
	}

	// Element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}