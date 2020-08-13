package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Textbox_TextArea_ {
	WebDriver driver;
	String userIDValue, password, loginPageUrl, customerID;
	String name, dateOfBirth, address, city, state, pin, phone, email, gender;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;

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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// User (Input data) - New Customer
		name = "John Terry";
		gender = "male";
		dateOfBirth = "1986-01-01";
		address = "105 Le Lai\nHai Chau\nDa Nang";
		city = "Los Angeles";
		state = "Califonia";
		pin = "326542";
		phone = "0987655333";
		email = "johnterry" + randomNumber() + "@hotmail.com";

		// User (Input data) - Edit Customer
		editAddress = "105 Ho Cam\nThanh Binh\nCan Tho";
		editCity = "New York";
		editState = "NewYesey";
		editPin = "222888";
		editPhone = "0987888999";
		editEmail = "johnterry" + randomNumber() + "@live.com";

		driver.get("http://demo.guru99.com/v4/");
		loginPageUrl = driver.getCurrentUrl();
	}

	@Test
	public void TC_01_Register() {
		// Click to 'Here' link
		driver.findElement(By.xpath("//a[text()='here']")).click();

		// Input to Email ID textbox
		driver.findElement(By.name("emailid")).sendKeys(email);

		// Click to Submit button
		driver.findElement(By.name("btnLogin")).click();

		// Get UserID/ Password value
		userIDValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);

		// Input to EmailID/ Password textbox
		driver.findElement(By.name("uid")).sendKeys(userIDValue);
		driver.findElement(By.name("password")).sendKeys(password);

		// Click to Submit button
		driver.findElement(By.name("btnLogin")).click();

		// Verify navigate to Manager page success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() {
		// Click to New Customer link
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Input to Required (Mandantory) Fields
		driver.findElement(customerNameTextbox).sendKeys(name);
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

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_Edit_Customer() {
		// Click to Edit Customer
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

		// Input to Customer ID textbox
		driver.findElement(By.name("cusid")).sendKeys(customerID);

		// Click to Submit button
		driver.findElement(By.name("AccSubmit")).click();

		// Verify Edit Customer form infor matching with New Customer infor
		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthTextbox).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressTextArea).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);
		
		sendkeyToElement("//input[@name='emailid']", phone);

		// Verify Name/ Gender/ Data of birth are disabled fields (read-only)
		Assert.assertFalse(driver.findElement(customerNameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirthTextbox).isEnabled());
		
		Assert.assertEquals(driver.findElement(dateOfBirthTextbox).isEnabled(), false);

		// Edit data at Edit Customer form
		
		// Xóa dữ liệu đi
		driver.findElement(addressTextArea).clear();
		
		// Nhập data mới vào
		driver.findElement(addressTextArea).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(editState);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(editPin);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(editPhone);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(editEmail);

		// Click to Submit button
		driver.findElement(By.name("sub")).click();

		// Verify update Customer success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer details updated Successfully!!!");

		// Verify input data (User) matching with output data (Server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
		String boxContentValue = driver.findElement(By.xpath("//div[@class='box-content']/p")).getText();
		Assert.assertTrue(boxContentValue.contains("Automation Testing"));
		Assert.assertTrue(boxContentValue.contains("autoamtio@gma..com"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sendkeyToElement(String locatorXpath, String value) {
		WebElement element = driver.findElement(By.xpath(locatorXpath));
		element.clear();
		element.sendKeys(value);
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}