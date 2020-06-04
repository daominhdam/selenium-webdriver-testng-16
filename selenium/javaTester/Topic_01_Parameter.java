package javaTester;

public class Topic_01_Parameter {

	// Param

	public static void main(String[] args) {
		System.out.println(showName());
		System.out.println(showName("Automation API"));
		System.out.println(showName("Manual", "Testing"));

		int number = 1000;
		System.out.println("Sum link = " + number);
	}

	// Fix
	public static String showName() {
		return "Automation Unit";
	}

	// Dynamic Fullname
	public static String showName(String fullName) {
		return fullName;
	}

	// Dynamic F + L
	public static String showName(String firstname, String lastname) {
		return firstname + " " + lastname;
	}

}
