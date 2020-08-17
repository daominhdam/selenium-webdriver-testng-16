package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
	@Test
	public void TC_01() {
		int a = 10;
		int b = 15;
		
		boolean status = a < b;
		System.out.println(status);
		
		// Ham kiem tra 1 dieu kien mong muon tra ve dung
		Assert.assertTrue(status); // Pass
		
		// Ham kiem tra 1 dieu kien mong muon tra ve sai
		Assert.assertFalse(a > b); // Pass
		
		// Ham kiem tra 2 dieu kien mong muon tra ve bang nhau
		Assert.assertEquals(a, 11); // Pass
		
		// AssertJ/ Hamcrest
	}
}
