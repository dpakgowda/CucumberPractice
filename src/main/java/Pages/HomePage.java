package Pages;

import Utils.ReadConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    //Constructor
    public HomePage(){
        super();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "ebayLogoTitle")
    public WebElement ebayTitle;
    @FindBy(xpath = "//a[contains(text(),'Advanced')]")
    public WebElement advancedButton;

    ReadConfig config = new ReadConfig();

    public void getEbayHomePage(){
        getDriver().get(config.eBayHomePage());
    }
}
