package javaTester;

public class Topic_04_People {
	// Biến = Thuộc tính = Field
	// Biến toàn cục (global): Sử dụng trong toàn bộ Class
	private String fullName = "Nguyen Van Troi";
	public String address = "245 Le Lai";

	// Hàm/ Phương thức = Method/ Function

	// Biến cục bộ (local): Chỉ sử dụng trong method

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

}
