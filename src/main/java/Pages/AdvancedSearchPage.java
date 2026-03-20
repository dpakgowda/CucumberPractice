package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindAll;
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
    @FindBy(xpath = "//*[@aria-labelledby='ebayLogoTitle']")
    public WebElement ebayTitlelist;
    //@FindBy(xpath = "//label[contains(text(),'Enter keywords or item number')]")
    @FindBy(id = "_nkw")
    public WebElement searchKeyword;
    @FindBy(id = "_ex_kw")
    public WebElement exclude;
    @FindBy(name = "_udlo")
    public WebElement minimum;
    @FindBy(name = "_udhi")
    public WebElement maximum;
    @FindBy(xpath = "(//*[@type='submit'])[2]")
    public WebElement Search;


}
