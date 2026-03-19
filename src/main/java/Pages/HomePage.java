package Pages;

import Utils.ReadConfig;
import org.openqa.selenium.By;
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
    @FindBy(xpath = "//*[@title=\"Search\"]")
    public WebElement searchField;
    @FindBy(xpath = "//span[contains(text(),'Search')]")
    public WebElement searchButton;
    @FindBy(xpath = "//span[contains(text(),'Shop by category')]")
    public WebElement shopByCategoryButton;
    @FindBy(xpath = "(//*[contains(text(),'Baby Essentials')])[1]")
    public WebElement babyEssentialsButton;
    @FindBy(css = "a[href*='Bathing-Grooming']")
    public WebElement bathingGroomingButton;
    @FindBy(css = "a[href*='Baby-Shampoos-Soaps']")
    public WebElement babyShampooSoap;
    @FindBy(xpath = "//span[contains(@class,'brw-controls__count')]")
    public WebElement result;
    @FindBy(xpath = "//h1[contains(@class,'textual-display page-title')]")
    public WebElement ebayCategoryTitle;

    ReadConfig config = new ReadConfig();

    public void getEbayHomePage(){
        getDriver().get(config.eBayHomePage());
    }

    public int getSearchCount(String searchText){
        String xpath = "//span[contains(text(),'"+ searchText + "') and @class='BOLD']/preceding-sibling::span";
        WebElement element = driver.findElement(By.xpath(xpath));
        waitForVisibility(element);
        String text = element.getText();

        return Integer.parseInt(text.replaceAll("[^0-9]",""));
    }
    public int getResultCount(){
        return Integer.parseInt(result.getText().replaceAll("[^0-9]",""));
    }
    public void clickCategory(String category){
        String xpath = "//span[contains(text(),'"+ category + "')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        waitForVisibility(element);
        click(element);
    }
}
