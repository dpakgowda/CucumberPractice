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

    ReadConfig config = new ReadConfig();

    public void getEbayHomePage(){
        getDriver().get(config.eBayHomePage());
    }

    public int getSearchCount(String searchText){
        String xpath = "//span[contains(text(),'"+ searchText + "') and @class='BOLD']/preceding-sibling::span";
        WebElement element = driver.findElement(By.xpath(xpath));
        waitForVisibility(element);
        String text = element.getText();

        System.out.println("Main + Feature branch change");

        return Integer.parseInt(text.replaceAll("[^0-9]",""));
    }
}
