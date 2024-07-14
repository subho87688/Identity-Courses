package TestData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;


public class BaseClass {

	

	public static WebDriver driver;
	public Properties p;
	public  Logger loger;
	WebDriverWait wait;
	
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());		// time stamp creation
	
	
	@BeforeClass(groups = {"sanity","regression"})
	@Parameters("browser")
	public void setUp(String br) throws IOException 
	{
		 //loading properties file
		 FileReader file=new FileReader(".//src//test//resources//Config.properties");
		 p=new Properties();
		 p.load(file);
		 
		 //load the logger file
		 loger=LogManager.getLogger(this.getClass());
		 
		 
		//launching browser based on condition
		if(br.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("No matching browser..");
			return;
		}
		
		//Explicit wait
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        
        //opening the web application		
        driver.get(p.getProperty("appUrl"));
        
        
	}
	
	
	
	
	
	@AfterClass(groups = {"sanity","regression"})
	public void tearDown()
	{
		driver.quit();		//close all the session
	}
	
	
	//To capture screen shot
	public String captureScreen(String tname) throws IOException 
	{
			
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
		String targetFilePath=System.getProperty("user.dir")+"\\ScreenShots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
			
		Files.copy(sourceFile, targetFile);
		
				
		return targetFilePath;

	}
	
}
