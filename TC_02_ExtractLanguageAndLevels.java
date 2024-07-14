package TestData;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageData.CourseraHomePage;
import PageData.LanguageLearningPage;
import Utilities.ExcelData;

public class TC_02_ExtractLanguageAndLevels extends BaseClass{

	CourseraHomePage hm;
	LanguageLearningPage llp;
	ExcelData exl;
	
	String fileName = "Language&level-" + timeStamp + ".xlsx";
	String pathName = ".//ExcelFiles//" + fileName;				//defining the path of the excel file
	
	
	@Test(priority=1, groups = {"sanity","regression"})
	public void checkCourseraHomePage()
	{
		loger.info("---------Starting the TC_02_ExtractLanguageAndLevels----------");
		loger.info("*Opening the Home Page of Coursera*");
		
		String actualTitle = driver.getTitle();					//storing the actual value 
		String expTitle = p.getProperty("homePageTitle");		//storing the expected value
		
		//checking wheather the right webpage is opened or not.
		Assert.assertEquals(actualTitle, expTitle);
	}
	
	
	
	@Test(priority = 2, dependsOnMethods = {"checkCourseraHomePage"}, groups = {"sanity","regression"})
	public void markLanguageLearn()
	{
		hm = new CourseraHomePage(driver);			//create object of Home Page Class
		llp = new LanguageLearningPage(driver);		//create object of Language learning Page Class
		
		//click on search button
        hm.clickSearch();
        
        loger.info("Clicking the Search button...");

        //click show more
        llp.clickFirstShowMore();

        //check the language learning checkBox
        llp.clickLangLearn();
        
        loger.info("Click the language learning button...");
        
        String actual = llp.getTestIdValue();
        String expected = p.getProperty("expectedLangLernAttriValue");
        
        //checks wheather the language Learn checkBox is clicked or not
        Assert.assertEquals(actual, expected);
	}
	
	
	
	@Test(priority = 3, dependsOnMethods = {"markLanguageLearn"}, groups = {"regression"})
	public void extractAllLanguage() throws Exception
	{
		llp = new LanguageLearningPage(driver);
		
		//click apply button
        llp.clickApplyButton();
        
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(llp.langLrnTag));
        
        //click show more
        llp.clickSecondShowMore();

        //extract all languages
        List<WebElement> allLang = llp.allLanguage;
        
        loger.info("Capturing all the Languages visible...");

        System.out.println("Total number of language available is : " + allLang.size());

        
        //print all the data in the console
        int i=0;
        for(WebElement ele: allLang)
        {
            String temp = ele.getText();
            System.out.println(++i + ") " + temp.substring(0,temp.indexOf("(")-1));
        }
        
		
        
        //Passing the Excel File to store all the datas
        exl = new ExcelData(pathName);
        
        //setting the header name for the excel sheet
        exl.setCellData("Language", 0, 0, "Sl. No.");			
        exl.setCellData("Language", 0, 1, "LANGUAGES");
        
        i=1;
        for(WebElement ele: allLang)			//add all the language datas into excel sheet
        {
        	String temp = ele.getText();
        	exl.setCellData("Language", i, 0, i+"");
            exl.setCellData("Language", i, 1, temp.substring(0,temp.indexOf("(")-1));
            i++;
        }
        
        //set the total language in the excel sheet
        exl.setCellData("Language", i+1, 0, "Total Language present -");
        exl.setCellData("Language", i+1, 1, allLang.size()+"");
        
        loger.info("Passing all the languages into the excel sheet...");
        
        
	}
	
	
	
	@Test(priority = 4, dependsOnMethods = {"markLanguageLearn"}, groups = {"regression"})
	public void extractAllLevel() throws IOException
	{
		//click cancel
        llp.clickCancel();
        
		llp = new LanguageLearningPage(driver);			//create object of Language learning Page Class
		
		//capture all the levels
        List<WebElement> allLevel = llp.allLevels;
        
        loger.info("Capturing all the Levels available...");

        System.out.println("\nAll levels available: ");

        int i=0;
        for (WebElement ele: allLevel)
        {
            String temp = ele.getText();
            System.out.println(++i + ") " + temp.substring(0,temp.indexOf("(")-1));
        }
        
		
        //Passing the Excel File to store all the datas
        exl = new ExcelData(pathName);
        
        //setting the header name for the excel sheet
        exl.setCellData("Level", 0, 0, "Sl. No.");
        exl.setCellData("Level", 0, 1, "LEVELS");
        
        i=1;
        for (WebElement ele: allLevel)			//add all the levels datas into excel sheet
        {
        	String temp = ele.getText();
        	exl.setCellData("Level", i, 0, i+"");
            exl.setCellData("Level", i, 1, temp.substring(0,temp.indexOf("(")-1));
            i++;
        }
        
        //set the total language in the excel sheet
        exl.setCellData("Level", i+1, 0, "Total no.of Levels present -");
        exl.setCellData("Level", i+1, 1, allLevel.size()+"");
        
        loger.info("Passing all the values into the Excel sheet...");
        loger.info("---------Ending of the TC_02_ExtractLanguageAndLevels----------");
		
        //scrolling to capture the elements
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,620)","");
        
	}
	
}
