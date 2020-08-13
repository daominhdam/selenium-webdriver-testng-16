package javaTester;

public class Topic_10_Path {
	static String source_folder = System.getProperty("user.dir");
	static String absolute_path = "G:\\project - training\\Online_Class_16\\02 - Selenium API\\selenium-webdriver-testng-16\\uploadFiles\\Image_01.jpg";
	static String relative_path = source_folder + "\\uploadFiles\\Image_01.jpg";
	String imageName = "Image_01.png";
	
	public static void main(String[] args) {
		Topic_10_Path topic10 = new Topic_10_Path();
		
		System.out.println(source_folder);
		System.out.println(absolute_path);
		System.out.println(relative_path);
		System.out.println(topic10.imageName);
	}

}
