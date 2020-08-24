package testng;

import java.util.Random;

import org.testng.annotations.Test;

public class Topic_07_Loop {

	@Test(invocationCount = 100)
	public void TC_01_Login_To_System() {
		System.out.println("Run TC_01_Login_To_System: " + randomNumber());
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}
