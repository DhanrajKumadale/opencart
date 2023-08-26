package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"Sanity", "Master"})
	public void test_login() {
		
		try {
			logger.info(" *** Starting TC_002_LoginTest ***");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			
			hp.clickLogin();
			logger.info("Clicked on Login Link");
			
			LoginPage lp= new LoginPage(driver);
			
			logger.info("Providing Logging details");
			lp.setEmail(rb.getString("email")); // valid email, get it from properties file
			lp.setPassword(rb.getString("password")); // valid password, get it from properties file
			lp.clickLogin();
			logger.info("Clicked on Login Button");
			
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExist();
			
			Assert.assertEquals(targetpage, true, "Invalid data provided");
			
		}
		catch(Exception e){
			Assert.fail();
		}
	}
}
