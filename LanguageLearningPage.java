package PageData;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LanguageLearningPage extends BasePage{

	public LanguageLearningPage(WebDriver driver) {
		super(driver);
	}
	
	
	//Language learn show more 
	@FindBy(xpath="(//span[@class='cds-button-label' and text()='Show more'])[1]")
	public WebElement firstShowMore;
	
	//Language learn check box
//	@FindBy(xpath="//div[@id='checkbox-group']/div[11]")
//	public WebElement langLearnCheckBox;
	
	
	//all subjects values		
	@FindBy(xpath="//div[@id='checkbox-group']/div")
	public List<WebElement> subjects;
	
	
	//Language learning TAG in the webPage after clicking language learning checkbox
	@FindBy(xpath="//button[text()='Language Learning']")
	public WebElement langLrnTag;
	
	
	//apply Button
	@FindBy(xpath = "(//div[@class='css-1hllf5q']/button)[1]")
	public WebElement applyButton;
	
	//all languages show more
	@FindBy(xpath="(//span[@class='cds-button-label' and text()='Show more'])[2]")
	public WebElement secondShowMore;
	
	//all the languages visible
	@FindBy(xpath="//div[@id='checkbox-group']//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']/span")
	public List<WebElement> allLanguage;
	
	//cancel button
	@FindBy(xpath="//div[@class='css-jyd7rb']/button")
	public WebElement cancel;
	
	//all levels visible
	@FindBy(xpath="//div[@data-testid='search-filter-group-Level']//div[@class='cds-checkboxAndRadio-labelText']/span[1]/span")
	public List<WebElement> allLevels;
	
	
	
	
	
	
	//All Element actions.........
	
	
	
	
	//click the first show more 
	public void clickFirstShowMore()
	{
		firstShowMore.click();
	}
	
	
	//click the Language Learning checkbox 
	public void clickLangLearn()
	{
		//langLearnCheckBox.click();
		for(WebElement ele: subjects)
		{
			if(ele.getText().contains("Language Learning"))
			{
				ele.click();
				break;
			}
		}
	}
	
	
	//get data-testid attribute value to check it is marked or not
	public String getTestIdValue()
	{
		
		for(WebElement ele: subjects)
		{
			if(ele.getText().contains("Language Learning"))
			{
				return ele.getAttribute("data-testid");
			}
		}
		
		return null;
	}
	
	//click the Apply Button 
	public void clickApplyButton()
	{
		applyButton.click();
	}
	
	//click the second show more 
	public void clickSecondShowMore()
	{
		secondShowMore.click();
	}
	
	//click the cancel button 
	public void clickCancel()
	{
		cancel.click();
	}

}
