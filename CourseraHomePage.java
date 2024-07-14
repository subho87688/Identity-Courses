package PageData;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CourseraHomePage extends BasePage{
	
	public CourseraHomePage(WebDriver driver) {
		super(driver);
	}

	
	//Search Box to find courses
	@FindBy(xpath="//input[@placeholder='What do you want to learn?']")
	public WebElement searchBox;
	
	//search button
	@FindBy(xpath="//button[@class='nostyle search-button']/div")
	public WebElement searchButton;
	
	
	//enterprice button
	@FindBy(xpath="(//ul[@class='rc-SubFooterSection__content-column-links'])[5]/li[10]/a")
	public WebElement enterprice;
	
	
	
	
	
	//All Element actions..............
	
	
	public void searchWebDevCourse(String value)
	{
		searchBox.sendKeys(value);
	}
	
	
	public void clickSearch()
	{
		searchButton.click();
	}
	
	//click enterprice text link
	public void clickForEnterprice()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", enterprice);
		//enterprice.click();
	}
	
}
