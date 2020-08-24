package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_08_Dependency {

	@Test()
	public void TC_01_Register() {
		System.out.println("Run TC_01_Register");
		
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "TC_01_Register")
	public void TC_02_Login() {
		System.out.println("Run TC_02_Login");
	}

}
