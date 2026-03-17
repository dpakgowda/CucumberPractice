package Pages;

public class HomePage extends BasePage{

    //Constructor
    public HomePage(){
        super();
    }

    public void getEbayHomePage(){
        getDriver().get("https://www.google.com");
    }
}
