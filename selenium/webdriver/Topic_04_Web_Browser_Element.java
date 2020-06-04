package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Element {
	// Khai báo 1 cái biến đại diện cho WebDriver = instance
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khởi tạo Browser lên
		driver = new FirefoxDriver();
		
	}
	
	@Test
	public void TC_01_Web_Browser() {
		// Mở app cần test (AUT: Application/ System Under Testing)
		driver.get("https://www.facebook.com/"); //**
		driver.manage().window().maximize(); //**
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //**
		
		// Đóng browser nếu có duy nhất 1 tab/ đóng tab đang active (>1 tab)
		// driver.close();
		
		// Đóng browser: Chẳng quan tâm có bao nhiêu tab/ cửa sổ = đóng hết
		// driver.quit(); //**
		
		// Lấy ra Url của page hiện tại (tab đang đứng)
		System.out.println(driver.getCurrentUrl()); //**
		
		// Bằng nhau
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");
		
		// Lấy ra title của page hiện tại
		System.out.println(driver.getTitle());
		
		// HTML/ Css/ JS/ Jquery
		driver.getPageSource();
		
		// Windows/ Tab
		driver.getWindowHandle(); //**
		driver.getWindowHandles(); //**
		
		// Build framework
		// driver.manage().logs().get(BROWSER);
		
		// Chờ cho element được tìm thấy sau khoảng time được set (10s)
		// WebDriverWait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Chờ cho page được loading xong
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		// Chờ script được loading xong (JS)
		// Javascript Executor (Nhúng đoạn code JS vào browser/ element)
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		
		driver.manage().window().fullscreen();
		
		
		// Test GUI: Graphic User Interface
		// Font/ size/ color/ location/ position/ responsive (resolution)
		// set/get Position
		// set/get Size
		
		// Resolution: 1920 x 1080
		
		// Back về trang trước
		driver.navigate().back();
		
		// Forward về trang trước
		driver.navigate().forward();
		
		// Tải lại trang
		driver.navigate().refresh();
		
		// Mở ra 1 url mới (Tracking history)
		driver.navigate().to("https://www.facebook.com/");
		
		// 3 loại: 
		// window/ tab
		// alert
		// frame/ iframe
		driver.switchTo().alert(); //**
		
		driver.switchTo().window(""); //**
		
		driver.switchTo().frame(""); //**
	}

	public void TC_02_Web_Element() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit(); //**
	}

}