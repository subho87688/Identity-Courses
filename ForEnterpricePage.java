package PageData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForEnterpricePage extends BasePage{

	public ForEnterpricePage(WebDriver driver) {
		super(driver);
	}
	
	//solution button
	@FindBy(xpath="//div[@data-testid='ew_header_links']/div[2]/a")
	public WebElement solution;
	
	
	//Campus For courses button
	@FindBy(xpath="//p[@class='css-deiwnw' and text()='Coursera for Campus']")
	public WebElement coursesForCapmus;
	
	
	
	
	
	
	
	
	
	
	
	//click Solution button
	public void clickSolution()
	{
		solution.click();
	}
	
	
	//click Campus For courses button
	public void clickcourseCampus()
	{
		coursesForCapmus.click();
	}
	
	
	
	
	
	

}
