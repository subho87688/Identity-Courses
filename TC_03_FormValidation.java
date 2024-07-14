package TestData;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageData.CourseraCampusPage;
import PageData.CourseraHomePage;
import PageData.ForEnterpricePage;
import Utilities.ExcelData;
import Utilities.FormDataProvider;

public class TC_03_FormValidation extends BaseClass{

	
	CourseraHomePage hm;
	ForEnterpricePage ep;
	CourseraCampusPage ccp;
	ExcelData xcl;
	
	
	int i=1,j=0;
	
	@Test(priority=1, groups = {"sanity","regression"})
	public void checkCourseraHomePage()
	{
		loger.info("---------Starting the TC_03_FormValidation----------");
		loger.info("*Opening the Home Page of Coursera*");
		
		String actualTitle = driver.getTitle();					//storing the actual value 
		String expTitle = p.getProperty("homePageTitle");		//storing the expected value
		
		//checking wheather the right webpage is opened or not.
		Assert.assertEquals(actualTitle, expTitle);
	}
	
	
	
	@Test(priority = 2, dependsOnMethods = {"checkCourseraHomePage"}, groups = {"sanity","regression"})
	public void searchTheForm()
	{
		hm = new CourseraHomePage(driver);			//instantiate the Home Page object
		ep = new ForEnterpricePage(driver);			//instantiate the For Enterprice Page object
		ccp = new CourseraCampusPage(driver);		//instantiate the Coursera Campus Page object
		
		//click on for enterprice
        hm.clickForEnterprice();
        
        loger.info("Click the For Enterprice button...");

        //click the solutions
        ep.clickSolution();
        
        loger.info("Click the Solution button...");

        //click courses for campus
        ep.clickcourseCampus();
        
        loger.info("Click for the Courses for Camous Link...");

		String actualFormName = ccp.getFormNameText();
		String expectedFormName = p.getProperty("expectedFormName");
		
		//scroll to view the form and it's name
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", ccp.firstName);
		js.executeScript("window.scrollBy(0,-300)","");
		
		Assert.assertEquals(actualFormName, expectedFormName, "Form is not visible....");
	}
	
	
	
	@Test(priority = 3, dependsOnMethods = {"searchTheForm"}, dataProvider="FormData", dataProviderClass= FormDataProvider.class, groups = {"regression"})
	public void getInvalidMessages(String fname, String lname, String mail, String phn, String instTyp, String instName, String job, String dpt,String need, String cntry, String ste) throws IOException
	{
		
		ccp = new CourseraCampusPage(driver);
		xcl = new ExcelData(".//ExcelFiles//FinalSheet.xlsx");
		
		if(i==1)
		{	loger.info("Starting the form validation...");	}
		
		if(j==0)
		{
			driver.navigate().refresh();		//refreshing the page everytime to get the empty text boxes
		}else {
			driver.navigate().back();			//redirecting the current page to form page
		}
		
		
		//passing datas into the form 
        ccp.setFirstName(fname);					//provide First name
        ccp.setLastName(lname);						//provide Last name
        ccp.setMail(mail);							//provide Work Email Address
        ccp.setPhoneNumber(phn);					//provide Phone Number
        ccp.setInstituiteType(instTyp);				//provide Institute Type
        ccp.setInstituteName(instName);				//provide Institute Name
        ccp.setJobRole(job);						//provide Job Role
        ccp.setDepartment(dpt);						//provide Department
        ccp.setNeeds(need);							//provide Which best describes your needs
        ccp.setCountry(cntry);						//provide Country
        ccp.setState(ste);							//provide State
        
        //Submit the form 
        ccp.submitForm();
        
        String actualMessage;
        
        
        //scroll to view the errors viewed.
        JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("window.scrollBy(0,-220)","");
        
        
    	//Writing the actual messages recived from the Web Pages
        if(ccp.thankYouMsg.size()!=0)
        {
        	//capture the Successful meaasage registration meassage.
        	actualMessage = ccp.getWelcomeMsg();
        	
        	System.out.println(i + ") Displayed welcome message is: ");
            System.out.println(actualMessage);
        	
        	//set the Welcome message recived into the excel sheet
            xcl.setCellData("Sheet1", i, 11, actualMessage);
            
        	j=1;
        }
        else 
        {
        	//capture the error message
        	actualMessage = ccp.getErrorMessage();
            
            
            System.out.println(i + ") Displayed error message is: ");
            System.out.println(actualMessage);
            
        	//set the error message recived into the excel sheet
            xcl.setCellData("Sheet1", i, 11, actualMessage);
            j=0;
        }
        
        
        String expectedMessage = xcl.getCellData("Sheet1", i, 12);
                
        loger.info("Validation with values at row : " + i++);
        
        
        if(i==12)
        {
        	loger.info("Ending of the form validation.");
        	loger.info("---------Ending of the TC_03_FormValidation----------");
        }
        
//        try {
//        	
//        	Assert.assertEquals(actualMessage, expectedMessage);
//        	xcl.setCellData("Sheet1", i-1, 14, "PASS");
//        
//        }catch(Exception e)
//        {
//        	e.printStackTrace();
//        	xcl.setCellData("Sheet1", i-1, 14, "FAIL");
//        }
        
        Assert.assertEquals(actualMessage, expectedMessage);
        
	}
	
}
