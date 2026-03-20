package StepDefination;

import Managers.PageManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class EbayAdvance {
    PageManager pages = new PageManager();

    @Given("I am Ebay Advanced Search Page")
    public void i_am_ebay_advanced_search_page() {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().getEbayHomePage();
        pages.getHomePage().isDisplayed(pages.getHomePage().ebayTitle);
        Assert.assertEquals(pages.getHomePage().getText(pages.getHomePage().ebayTitle).toLowerCase(),"ebay home");

        pages.getHomePage().isDisplayed(pages.getHomePage().advancedButton);
        Assert.assertEquals(pages.getHomePage().getText(pages.getHomePage().advancedButton).toLowerCase(),"advanced");

        pages.getAdvancedSearchPage().click(pages.getHomePage().advancedButton);
        pages.getHomePage().isDisplayed(pages.getHomePage().ebayTitle);
        pages.getAdvancedSearchPage().isDisplayed(pages.getAdvancedSearchPage().advancedSearchTitle);
    }

    @When("I click on Ebay Logo")
    public void i_click_on_ebay_logo() {
        // Write code here that turns the phrase above into concrete actions
        pages.getAdvancedSearchPage().isDisplayed(pages.getAdvancedSearchPage().ebayTitlelist);
        pages.getHomePage().click(pages.getAdvancedSearchPage().ebayTitlelist);
    }
    @Then("" +
            "I am navigated to Ebay Home Page")
    public void i_am_navigated_to_ebay_home_page() {
        // Write code here that turns the phrase above into concrete actions
        pages.getHomePage().isDisplayed(pages.getHomePage().ebayTitle);
        Assert.assertEquals(pages.getHomePage().getText(pages.getHomePage().ebayTitle).toLowerCase(),"ebay home");
    }

    @When("I advanced search an item")
    public void i_advanced_search_an_item(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<List<String>> rows = dataTable.asLists(String.class);

        pages.getAdvancedSearchPage().isDisplayed(pages.getAdvancedSearchPage().searchKeyword);
        //pages.getAdvancedSearchPage().click(pages.getAdvancedSearchPage().searchKeyword);
        pages.getAdvancedSearchPage().jsClick(pages.getAdvancedSearchPage().searchKeyword);
        pages.getAdvancedSearchPage().setValueUsingJS(pages.getAdvancedSearchPage().searchKeyword, rows.get(1).get(0));

        pages.getAdvancedSearchPage().click(pages.getAdvancedSearchPage().exclude);
        pages.getAdvancedSearchPage().sendKeys(pages.getAdvancedSearchPage().exclude, rows.get(1).get(1));

        pages.getAdvancedSearchPage().scrollToElement(pages.getAdvancedSearchPage().minimum);
        pages.getAdvancedSearchPage().click(pages.getAdvancedSearchPage().minimum);
        pages.getAdvancedSearchPage().sendKeys(pages.getAdvancedSearchPage().minimum, rows.get(1).get(2));

        pages.getAdvancedSearchPage().click(pages.getAdvancedSearchPage().maximum);
        pages.getAdvancedSearchPage().sendKeys(pages.getAdvancedSearchPage().maximum, rows.get(1).get(3));
        pages.getAdvancedSearchPage().scrollToElement(pages.getAdvancedSearchPage().Search);
        pages.getAdvancedSearchPage().click(pages.getAdvancedSearchPage().Search);

        pages.getHomePage().waitForVisibility(pages.getHomePage().Result);
        Assert.assertTrue(pages.getHomePage().Result.getText().contains(rows.get(1).get(1)));
    }
}
