package javaTester;

public class Topic_06_Replace {

	public static void main(String[] args) {
		// User
		String inputAddress = "105 Le Lai\nHai Chau\nDa Nang";
		
		// Server
		String outputAddress = "105 Le Lai Hai Chau Da Nang";
		
		
		inputAddress = inputAddress.replace("\n", " ");
		
		System.out.println(inputAddress);
		System.out.println(outputAddress);
		

	}

}
