package testng;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testng.TestNGListener.class)
public class Topic_09_Listener {

	@Test()
	public void TC_01_Register() {
		System.out.println("Run TC_01_Register");
		
	}

	@Test()
	public void TC_02_Login() {
		System.out.println("Run TC_02_Login");
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "TC_02_Login")
	public void TC_03_New() {
		System.out.println("Run TC_03_New");
	}

}
