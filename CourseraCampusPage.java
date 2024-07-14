package PageData;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CourseraCampusPage extends BasePage{

	public CourseraCampusPage(WebDriver driver) {
		super(driver);
	}
	
	
	//Form name displayed
	@FindBy(xpath="(//h2[@data-testid='how_module_hero_heading'])[4]")
	public WebElement formName;
	
	//first name text box
	@FindBy(xpath="//input[@id='FirstName']")
	public WebElement firstName;
	
	//last name text box
	@FindBy(xpath="//input[@id='LastName']")
	public WebElement lastName;
	
	//Work email text box
	@FindBy(xpath="//input[@id='Email']")
	public WebElement mail;
	
	//phone number text box
	@FindBy(xpath="//input[@id='Phone']")
	public WebElement phone;
	
	//institute text box
	@FindBy(xpath="//select[@id='Institution_Type__c']")
	public WebElement instituteNames;
	
	//company text box
	@FindBy(xpath="//input[@id='Company']")
	public WebElement company;
	
	//Job title drop down
	@FindBy(xpath="//select[@id='Title']")
	public WebElement title;
	
	//Department drop down
	@FindBy(xpath="//select[@id='Department']")
	public WebElement department;
	
	//Whats' needed drop down
	@FindBy(xpath="//select[@id='What_the_lead_asked_for_on_the_website__c']")
	public WebElement needs;
	
	//country drop down
	@FindBy(xpath="//select[@id='Country']")
	public WebElement countries;
	
	//state text box
	@FindBy(xpath="//select[@id='State']")
	public WebElement states;
	
	//form submit button
	@FindBy(xpath="//button[@class='mktoButton']")
	public WebElement submit;
	
	//error message
	@FindBy(xpath="//div[@class='mktoErrorMsg']")
	public WebElement errorMessage;
	
	
	//Thank you message
	@FindBy(xpath="//h1[text()='Thank you for your interest in Coursera for Campus']")
	public List<WebElement> thankYouMsg;
	
	
	
	//All element the actions.........
	
	
	
	
	//return the name of the form 
	public String getFormNameText()
	{
		return formName.getText();
	}
	
	//Click on submit 
	public void submitForm()
	{
		submit.click();
	}
	
	//return the error message appeared for each text box
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}

	//send the first name to the name text box in form
	public void setFirstName(String fname)
	{
		firstName.sendKeys(fname);					//provide First name
	}
	
	
	public void setLastName(String lname)
	{
		lastName.sendKeys(lname);				//provide Last name
	    
	}
	
	public void setMail(String mailAdrs)
	{
		mail.sendKeys(mailAdrs);				//provide Work Email Address
	}
	
	public void setPhoneNumber(String phn)
	{
		phone.sendKeys(phn);				//provide the phone number
	}
	
	
	public void setInstituiteType(String instTyp)
	{
		Select institute = new Select(instituteNames);		
	    institute.selectByVisibleText(instTyp);					//provide Institute Type
	}
	
	
	public void setInstituteName(String instName)
	{
		company.sendKeys(instName);					//provide Institute Name
	}
	
	public void setJobRole(String job)
	{
		Select titl = new Select(title);
	    titl.selectByVisibleText(job);				//provide Job Role

	}
	
	public void setDepartment(String dpt)
	{
		Select dprtmnt = new Select(department);
		dprtmnt.selectByVisibleText(dpt);				//provide Department
	}
	
	public void setNeeds(String need)
	{
		Select neds = new Select(needs);
	    neds.selectByVisibleText(need);				//provide Which best describes your needs
	}
	
	public void setCountry(String cntry)
	{
		Select country = new Select(countries);
	    country.selectByVisibleText(cntry);				//provide Country
	}
	
	public void setState(String ste)
	{
		Select state = new Select(states);
	    state.selectByVisibleText(ste);					//provide State
	}
	
    
	public String getWelcomeMsg()
	{
		return thankYouMsg.get(0).getText();
	}
    
    

    

    
    
    

    
	
	
	
	
	
	
}
