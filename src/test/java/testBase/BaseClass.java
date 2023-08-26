package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;  // logging

//import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; // for logging
	public ResourceBundle rb; // to get the properties from file
	
	@BeforeClass(groups= {"Regression","Sanity", "Master"})
	@Parameters("browser")
	public void setup(String br)
	{
		logger = LogManager.getLogger(this.getClass()); // this class represents current running class logs.  we can get using line
		
		rb=ResourceBundle.getBundle("config");
		
	    WebDriverManager.chromedriver().setup(); // Not required for latest release
		
     // Below Chrome option will disable the "Chrome is being controlled by automated test software"
	/*	ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); */
		//driver = new ChromeDriver(options); 
		
		if(br.equals("chrome")) {
			driver = new ChromeDriver();
			}
		else if(br.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new FirefoxDriver();
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(rb.getString("appURL1"));
		driver.manage().window().maximize(); //OR
	//	JavascriptExecutor js1=(JavascriptExecutor)driver;
	//	js1.executeScript("document.body.style.zoom='80%';");
	}
	
	@AfterClass(groups= {"Regression","Sanity", "Master"})
	public void tearDown()
	{
		driver.quit();	
	}

	// below 3 are java methods for random data generation, hence not using any annotations
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	// We need to pass the string to the method, hence no is storing to string format
	public String randomNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return generatedString2;
	}
	// Combine and pass
	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		return (st+"@"+num);
	}

	public String captureScreen(String tname) throws IOException{

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
	
	
}
