package StepDefination;

import Managers.PageManager;
import Utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EbayHome extends DriverFactory {

    PageManager pages = new PageManager();
    public int count;

    @Given("I am on Ebay Home Page")
    public void i_am_on_ebay_home_page() {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().getEbayHomePage();
        pages.getHomePage().isDisplayed(pages.getHomePage().ebayTitle);
        Assert.assertEquals(pages.getHomePage().getText(pages.getHomePage().ebayTitle).toLowerCase(),"ebay home");
    }

    @When("I click on Advanced Link")
    public void i_click_on_advanced_link() {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().isDisplayed(pages.getHomePage().advancedButton);
        Assert.assertEquals(pages.getHomePage().getText(pages.getHomePage().advancedButton).toLowerCase(),"advanced");
    }

    @Then("I navigate to Advanced Search page")
    public void i_navigate_to_advanced_search_page() {
        // Write code here that turns the phrase above into concrete actions
        pages.getAdvancedSearchPage().click(pages.getHomePage().advancedButton);
        pages.getHomePage().isDisplayed(pages.getHomePage().ebayTitle);
        pages.getAdvancedSearchPage().isDisplayed(pages.getAdvancedSearchPage().advancedSearchTitle);
        Assert.assertEquals(pages.getAdvancedSearchPage().getText(pages.getAdvancedSearchPage().advancedSearchTitle).toLowerCase(),"advanced search");
    }

    @When("I serach for {string}")
    public void i_serach_for(String string) throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().click(pages.getHomePage().searchField);
        pages.getHomePage().sendKeys(pages.getHomePage().searchField, string);
        pages.getHomePage().click(pages.getHomePage().searchButton);
        Thread.sleep(2000);
        count = pages.getHomePage().getSearchCount(string);
    }

    @Then("I validate atleast {int} search items present")
    public void i_validate_atleast_search_items_present(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(count>int1);
        count =0;
    }

    @When("I serach for {string} in {string} category")
    public void i_serach_for_in_category(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().click(pages.getHomePage().shopByCategoryButton);
        pages.getHomePage().jsClick(pages.getHomePage().babyEssentialsButton);
        pages.getHomePage().click(pages.getHomePage().bathingGroomingButton);
        pages.getHomePage().click(pages.getHomePage().babyShampooSoap);
        Assert.assertTrue(pages.getHomePage().getResultCount()>50);
    }

    @When("I click on {string}")
    public void i_click_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().clickCategory(string);
    }

    @Then("I validate that page navigates to {string} and title contains {string}")
    public void i_validate_that_page_navigates_to_and_title_contains(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().waitForVisibility(pages.getHomePage().ebayCategoryTitle);
        pages.getHomePage().waitForUrlContains(string);
        Assert.assertEquals(pages.getHomePage().getText(pages.getHomePage().ebayCategoryTitle),string2);
    }

}