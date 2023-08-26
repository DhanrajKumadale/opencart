package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {


	@Test(groups= {"Regression","Master"})
	void test_account_Registration() throws InterruptedException
	
	{
		logger.debug("application logs......");
		logger.info("*** Starting TC_001_AccountRegisterationTest ***");
		try
		{
		HomePage hp = new HomePage(driver);
		logger.info("Clicked on My account link");
		hp.clickMyAccount();
		logger.info("Clicked on My register link");
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer data");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com"); // random generated email.id
		
	//	regpage.setTelephone(randomNumber()); //In Web this step is not available
		
		String passowrd = randomAlphaNumeric();
		regpage.setPassword(passowrd);
	//	regpage.setConfirmPassword(passowrd);  //In Web this step is not available
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
		Thread.sleep(2000);
		regpage.setPrivacyPolicy();
		
		logger.info("Clicked on Continue button");
		
		Thread.sleep(2000);
		regpage.clickContinue();
		
		String conmsg =regpage.getConfirmationMsg();
	    logger.info("Validating expected message");	
		Assert.assertEquals(conmsg, "Your Account Has Been Created!"); 
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*** Finished TC_001_AccountRegisterationTest ***");
	}
	
}
