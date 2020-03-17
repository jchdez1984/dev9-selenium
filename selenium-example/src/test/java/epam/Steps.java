package epam;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import epam.pages.SearchPage;
import epam.pages.SearchResultsPage;
import junit.framework.AssertionFailedError;



public class Steps{
    private WebDriver driver;
    private Object currentPage;
    private String termEntered = "";
    private String currentTitle = "";
    

    @Before({"@requires_browser"})
    public void setUp(){
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        driver = new FirefoxDriver();
    }

    @After({"@requires_browser"})
    public void tearDown(){
        driver.close();
    }

    @Given("^a google page$")
    public void a_google_page() throws Throwable {
        currentPage = SearchPage.loadUsing(driver);
    }
    
    @When("^I enter the search term \"([^\"]*)\"$")
    public void i_enter_the_search_term(String term) throws Throwable {
        verifyCurrentPage(SearchPage.class);
        termEntered = term;
        ((SearchPage) currentPage).setQuery(term);
    }
    
    @When("^I submit the search by pressing \"([^\"]*)\"$")
    public void i_submit_the_search_by_pressing(String submitType) throws Throwable {
        switch(submitType){
            case "search":
                currentPage = ((SearchPage) currentPage).clickSearch();
                break;
            default:
                currentPage = ((SearchPage) currentPage).clickSearch();
                break;
        }
    }
    
    @Then("^the search result page title should contain the search term$")
    public void the_search_result_page_title_should_contain_the_search_term() throws Throwable {
        verifyCurrentPage(SearchResultsPage.class);
        currentTitle = ((SearchResultsPage) currentPage).getTitle();
        assertThat(currentTitle, CoreMatchers.containsString(termEntered));
    }

    private void verifyCurrentPage(Class expectedClass){
        if(!currentPage.getClass().equals(expectedClass)){
            throw new AssertionFailedError(
                String.format("Expected current page to have type %s - actual type is %s",
                expectedClass.getSimpleName(),
                currentPage.getClass().getSimpleName())
            );
        }
    }
    
}
