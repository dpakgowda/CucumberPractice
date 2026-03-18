package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvancedSearchPage extends BasePage {
    //Constructor
    public AdvancedSearchPage(){
        super();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(text(),'Advanced Search')]")
    public WebElement advancedSearchTitle;
}
