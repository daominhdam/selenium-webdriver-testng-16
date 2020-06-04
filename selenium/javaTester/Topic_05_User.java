package javaTester;

public class Topic_05_User {

	public static void main(String[] args) {
		// Muốn sử dụng được thuộc tính/ phương thức của 1 class khác
		Topic_04_People topic04;

		topic04 = new Topic_04_People();
		
		// Sử dụng phương thức của Class 04
		System.out.println(topic04.getFullName());
		topic04.setFullName("Le Loi");
		System.out.println(topic04.getFullName());
		
		// Sử dụng thuộc tính của Class 04
		System.out.println(topic04.address);
		
	}

}
