package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Priority_Skip {

	@BeforeClass
	public void beforeClass() {
		System.out.println("Run Before");
	}

	@Test(description = "Create new User to login to system")
	public void TC_01_Create_User() {
		System.out.println("Create_User");
	}
	
	@Test(description = "Edit the existing User")
	public void TC_02_Edit_User() {
		System.out.println("Edit_User");
	}
	
	@Test
	public void TC_03_View_User() {
		System.out.println("View_User");
	}
	
	@Test
	public void TC_04_Search_User() {
		System.out.println("Search_User");
	}

	@Test(enabled = false)
	public void TC_05_Delete_User() {
		System.out.println("Delete_User");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Run After");
	}

}
