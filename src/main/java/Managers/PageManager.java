package Managers;

import Pages.HomePage;
import Pages.AdvancedSearchPage;
public class PageManager {
    private HomePage homePage;
    private AdvancedSearchPage advancedSearchPage;

    public HomePage getHomePage(){
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public AdvancedSearchPage getAdvancedSearchPage(){
        if(advancedSearchPage == null){
            advancedSearchPage = new AdvancedSearchPage();
        }
        return advancedSearchPage;
    }

}
