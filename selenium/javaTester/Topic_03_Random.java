package javaTester;

import java.util.Random;

public class Topic_03_Random {

	public static void main(String[] args) {
		Topic_03_Random topic03 = new Topic_03_Random();
		// Instance
		
		System.out.println("john" + topic03.randomNumber() + "@gmail.com");
		System.out.println("john" + topic03.randomNumber() + "@gmail.com");
		System.out.println("john" + topic03.randomNumber() + "@gmail.com");
		
		System.out.println("john" + randomNumbers() + "@gmail.com");
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	public static int randomNumbers() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
