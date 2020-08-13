package javaTester;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_TextBox_TextArea {
	WebDriver driver;
	String userIDValue, password, loginPageURL, customerID;
	String name, dateOfBird, address, city, state, pin, phone, email, gender;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;

	By customerNameTextbox = By.name("name");
	By genderMaleRaidoButton = By.xpath("//input[@value='m']");
	By genderTextbox = By.name("gender");
	By dateOfBirdTextbox = By.name("dob");
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

		// User Input - New Customer
		name = "John Wick";
		gender = "male";
		dateOfBird = "1985-01-01";
		address = "113 Ngo Quyen\nSon Tra\nDa Nang";
		city = "California";
		state = "Washington";
		pin = "542121";
		phone = "0903321123";
		email = "johnwick" + randomNumber() + "@hotmail.com";

		// User Input - Edit Customer
		editAddress = "113 Tay Son\nNgu Hanh Son\nDa Nang";
		editCity = "California";
		editState = "Los Angeles";
		editPin = "542121";
		editPhone = "0903321123";
		editEmail = "johnwick" + randomNumber() + "@live.com";

		driver.get("http://demo.guru99.com/v4");
		loginPageURL = driver.getCurrentUrl();
	}
	// edit this line

	@Test
	public void TC_01_Register() {
		// Click to 'Here' link
		driver.findElement(By.xpath("//a[text()='here']")).click();

		// Input to EmailID textbox
		driver.findElement(By.name("emailid")).sendKeys(email);

		// Click to 'Submit' button
		driver.findElement(By.name("btnLogin")).click();

		// Get ID/Pass
		userIDValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageURL);
		driver.findElement(By.name("uid")).sendKeys(userIDValue);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		// Verify navigete to Manager page success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_New_Customer() throws InterruptedException {
		// Click to New Customer link
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		// Input to Required (Mandantory) Fields
		driver.findElement(customerNameTextbox).sendKeys(name);
		driver.findElement(dateOfBirdTextbox).sendKeys(dateOfBird);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		// Click to Submit button
		driver.findElement(By.name("sub")).click();

		// Verify create new customer success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer Registered Successfully!!!");
		// Verify input data(User) matching with output data (Server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBird);
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
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirdTextbox).getAttribute("value"), dateOfBird);
		Assert.assertEquals(driver.findElement(addressTextArea).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextbox).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);

		// Verify Name/Data of birth are disabled fields(read-only)
		Assert.assertFalse(driver.findElement(customerNameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(dateOfBirdTextbox).isEnabled());

		// Edit data at Edit Customer form
		driver.findElement(addressTextArea).clear();
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
 
		//Click to Submit
		driver.findElement(By.name("sub")).click();
		
		// Verify update customer success
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer details updated Successfully!!!");
		
		
		// Verify input data(User) matching with output data (Server response)
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBird);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}