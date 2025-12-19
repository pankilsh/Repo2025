package seleniumFrameworkDesign.Tests;

import org.testng.annotations.Test;

import org.testng.Assert;

import seleniumFrameworkDesign.TestComponents.BaseTest;
import seleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups = "errorHandling", retryAnalyzer = Retry.class)
	public void invalidEmail() {
		String user = "asdasd@dfd.com";
		String password = "Pass@12345";
		
		landingPage.loginToApplication(user, password);
		
		String expectedError = "Incorrect email or password.";
		String actualError = landingPage.getErrorMessage();
		
		Assert.assertEquals(actualError, expectedError);
		
	}

	@Test(groups = "errorHandling")
	public void invalidPassword() {
		String user = "pankil@gmail.com";
		String password = "Asdasdasd";
		
		landingPage.loginToApplication(user, password);
		
		String expectedError = "Incorrect email or password.";
		String actualError = landingPage.getErrorMessage();
		
		Assert.assertEquals(actualError, expectedError);
	}

}
