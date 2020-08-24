package testng;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("Testcase nay fail");
		System.out.println("Da chup hinh");
		System.out.println("Da add vao report");
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Testcase nay skip");
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Testcase nay pass");
		
	}
	// Class ke thua 1 class: extends
	// Class thuc hien 1 interface: implements
	
	

}
