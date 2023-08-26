package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void test_LoginDDT(String email, String password, String exp) // 3 parameters getting from excel sheet by using array
	{
		try 
		{
			logger.info(" *** Starting TC_002_LoginTest ***");
			
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on My Account");
			
			hp.clickLogin();
			logger.info("Clicked on Login Link");
			
			LoginPage lp= new LoginPage(driver);
			
			logger.info("Providing Logging details");
			lp.setEmail(email); // valid email, get it from excel sheet file
			lp.setPassword(password); // valid password, get it from excel sheet file
			lp.clickLogin();
			logger.info("Clicked on Login Button");
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage =macc.isMyAccountPageExist();
			
			if(exp.equals("Valid"))
				{
				  if(targetpage == true)
				  {
					  macc.clickLogout();
					  Assert.assertTrue(true);
				  }
				  else 
				  {
					Assert.assertTrue(false);  
				  }
				}
			if(exp.equals("Invalid"))
			{
			  if(targetpage == true)
			  {
				  macc.clickLogout();
				  Assert.assertTrue(false);
			  }
			  else 
			  {
				Assert.assertTrue(true);  
			  }
			}
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info(" *** Finished TC_002_LoginTest ***");
	}

}
