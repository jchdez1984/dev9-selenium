package epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import epam.util.TestConf;

public class SearchPage{
    private static final TestConf TEST_CONF = TestConf.getTestConf();
    private final WebDriver driver;

    @FindBy(name = "q")
    private WebElement input;

    @FindBy(css = "input.gNO89b")
    private WebElement searchButton;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public static SearchPage loadUsing(WebDriver driver) {
        driver.get(TEST_CONF.getSearchUrl());
		return new SearchPage(driver);
    }
    
    public SearchPage setQuery(String query){
        input.clear();
        input.sendKeys(query);
        return this;
    }

    public SearchResultsPage clickSearch(){
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        return new SearchResultsPage(driver);
    }

}