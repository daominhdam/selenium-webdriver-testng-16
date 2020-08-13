package javaTester;

public class Topic_09_Split {

	public static void main(String[] args) {
		String linkText = "http://the-internet.herokuapp.com/basic_auth";
		System.out.println(linkText);
		String linkSplit[] = linkText.split("//");
		System.out.println(linkSplit[0]);
		System.out.println(linkSplit[1]);
		
		linkText = linkSplit[0] + "//" + "admin" + ":" + "password" + "@" + linkSplit[1];
		System.out.println(linkText);

	}

}
