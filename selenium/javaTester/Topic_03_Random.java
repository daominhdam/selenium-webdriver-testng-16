package javaTester;

import java.util.Random;

public class Topic_03_Random {

	public static void main(String[] args) {
		String email = "john" + randomNumber() + "@gmail.com";
		System.out.println(email);
		System.out.println(email);
		System.out.println(email);
	}
	
	// cố định 1 số: 0 - 9
	// cố định 2 số: 0 - 99
	// cố định 3 số: 0 - 999
	// cố định 6 số: 0 - 999999

	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
