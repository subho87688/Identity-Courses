package PageData;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebDevelopmentPage extends BasePage{

		public WebDevelopmentPage(WebDriver driver) {
			super(driver);
		}
	
		//WebDevelopment Heading text
		@FindBy(xpath="//span[contains(text(),'Web Development Courses')]")
		public WebElement headingText;
		
		//english checkBox
		@FindBy(xpath="(//input[contains(@id,'cds-react-aria-')])[5]")
		public WebElement englishCheckBox;
		
		//Beginner checkBox
		@FindBy(xpath="//div[@class='cds-checkboxAndRadio-labelText']//span[text()='Beginner']//preceding::input[1]")
		public WebElement beginnerCheckBox;
		
		//list of course Name
		@FindBy(xpath="(//h3[contains(@class,'cds-CommonCard-title css-')])[position()<3]")
		public List<WebElement> coursesName;
		
		//list of courses Hours
		@FindBy(xpath="(//div[@class='cds-CommonCard-metadata'])[position()<3]")
		public List<WebElement> coursesHours;
			
		//list of courses Ratings
		@FindBy(xpath="(//div[@class='product-reviews css-pn23ng']/p[1])[position()<3]")
		public List<WebElement> coursesRatings;
		
	
		
		
		
		
		
		
		public String courseSearched()
		{
			String temp = headingText.getText();
			
			return temp.substring(temp.indexOf("\"")+1,temp.length()-1);
		}
		
		
		//click the English CheckBox
		public void clickEnglishChkBox()
		{
			englishCheckBox.click();
		}
		
		
		//click the Beginner CheckBox
		public void clickBeginnerChkBox()
		{
			beginnerCheckBox.click();
		}
		
		
		//return English CheckBox is Selected or not
		public boolean isEnglishBoxChecked()
		{
			return englishCheckBox.isSelected();
		}

		
		//return Beginner CheckBox is Selected or not
		public boolean isBeginnerBoxChecked()
		{
			return beginnerCheckBox.isSelected();
		}
		
		
		
	
}
