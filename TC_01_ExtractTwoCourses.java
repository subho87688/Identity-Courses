package TestData;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageData.CourseraHomePage;
import PageData.WebDevelopmentPage;
import Utilities.ExcelData;

public class TC_01_ExtractTwoCourses extends BaseClass{

	
	CourseraHomePage hm;
	WebDevelopmentPage wdp;
	ExcelData exl;
    
	String fileName = "WebDevCourses-" + timeStamp + ".xlsx";
	String pathName = ".//ExcelFiles//" + fileName;				//defining the path of the excel file
	
	
	
	@Test(priority=1, groups = {"sanity","regression"})
	public void checkCourseraHomePage()
	{
		loger.info("---------Starting the TC_01_ExtractTwoCourses----------");
		loger.info("*Opening the Home Page of Coursera*");
		
		String actualTitle = driver.getTitle();
		String expTitle = p.getProperty("homePageTitle");
		
		//checking wheather the right webpage is opened or not.
		Assert.assertEquals(actualTitle, expTitle);
	}
	
	
	@Test(priority=2, dependsOnMethods= {"checkCourseraHomePage"}, groups = {"sanity","regression"})
	public void searchValues()
	{
		hm = new CourseraHomePage(driver);
		wdp = new WebDevelopmentPage(driver);
		
		//search for the Web Development Courses
		hm.searchWebDevCourse(p.getProperty("course"));
		
		//click on search button
		hm.clickSearch();
		
		loger.info("Searching the WebDevelopment Courses...");
		
		
		//checking that the webDevelopment courses are searched or not
		String actualSearchedCourse = wdp.courseSearched();
		String expectedSearchedCourse = "Web Development Courses";
		
		Assert.assertEquals(actualSearchedCourse, expectedSearchedCourse, "Invalid Courses were searched.");
	}
	
	@Test(priority=3, dependsOnMethods= {"searchValues"}, groups = {"sanity","regression"})
	public void markRequiredFeilds() throws InterruptedException
	{
		//instantiate Web Development object
		wdp = new WebDevelopmentPage(driver);
		
		//click the the English checkBox
		wdp.clickEnglishChkBox();
		
		loger.info("Click the English ChechBox...");
		
		//click the the Beginner checkBox
		wdp.clickBeginnerChkBox();
		
		loger.info("Click the biginner checkbox...");
		
		wait.until(ExpectedConditions.elementToBeSelected(wdp.beginnerCheckBox));
		
		//if any of the button does not gets selected test will fail
		if(!(wdp.isEnglishBoxChecked() && wdp.isBeginnerBoxChecked()))
		{
			Assert.fail();
		}
		
	}
	
	
	@Test(priority=4, dependsOnMethods= {"searchValues"}, groups = {"regression"})
	public void captureFirstTwoCourse() throws IOException  
	{
		//instantiate HomePage object
		wdp = new WebDevelopmentPage(driver);
		
		//collecting the Courses Name
		List<WebElement> courseNameList = wdp.coursesName;
		
		loger.info("Extracting all the Courses Names...");
		
		//collecting the course hours
		List<WebElement> courseHoursList = wdp.coursesHours;
		
		loger.info("Extracting the Time duration for every Courses...");
		
		//collecting the course raitings
		List<WebElement> courseRatingList = wdp.coursesRatings;
		
		loger.info("Extracting all the ratings for every Courses...");
		
		System.out.println("Courses Name  <---->   Time Duration   <---->    Rating   ");
		
		
		//Passing the Excel File to store all the datas
        exl = new ExcelData(pathName);
        
        //set the header values into the excel sheet
        exl.setCellData("Courses", 0, 0, "COURSE NAME");
        exl.setCellData("Courses", 0, 1, "TIME");
        exl.setCellData("Courses", 0, 2, "RATINGS");
		
        //print all the courses values in console
		for(int i=0; i<2; i++)
		{
			System.out.print(i+1+") " + courseNameList.get(i).getText() + "-");
			String s1 = courseHoursList.get(i).getText();
			String sbstr = s1.substring(s1.indexOf("·", 13)+2);
			System.out.print("  "+sbstr);
			System.out.print(" -  "+courseRatingList.get(i).getText()+"\n");
		}
		
		loger.info("Inserting all the extracted values into the Excel file...");
		
		for(int i=0; i<2; i++)
		{
			//extract the time duration only from the entire string
			String s1 = courseHoursList.get(i).getText();
			String sbstr = s1.substring(s1.indexOf("·", 13)+2);
			
			//set the values into the excel sheet
			exl.setCellData("Courses", i+1, 0, courseNameList.get(i).getText());
			exl.setCellData("Courses", i+1, 1, sbstr);
			exl.setCellData("Courses", i+1, 2, courseRatingList.get(i).getText());
		}
		
		
		loger.info("--------- Ending of the TC_01_ExtractTwoCourses ----------");
		
		//scroll to get the courses into view
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,-550)","");
		
	}
	
}
