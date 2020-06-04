package javaTester;

public class Topic_02_String {

	public static void main(String[] args) {
		String name = "Selenium WebDriver API 16 !@#$%^&*()";
		
		String addrss = "		    Selenium		 ";
		
		int year = 2020;
		
		double price = 15.5;
		
		boolean status = true;
		
		System.out.println(name.contains("WebDriver"));
		System.out.println(name.contains("Selenium"));
		System.out.println(name.contains("API"));
		System.out.println(name.contains("nium Web"));
		System.out.println(name.contains("Grid"));
		
		System.out.println(name.equals("Selenium"));
		System.out.println(name.equals("WebDriver"));
		System.out.println(name.equals("WebDriver API"));
		System.out.println(name.equals("Selenium WebDriver API"));
		
		String selenium = "Hello \"John\", What'";
		String framework = "s happened?";
		System.out.println(selenium.concat(framework));
	}

}
