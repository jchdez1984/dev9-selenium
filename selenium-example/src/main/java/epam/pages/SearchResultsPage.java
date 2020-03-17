package epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import epam.util.TestConf;

public class SearchResultsPage{
    private static final TestConf TEST_CONF = TestConf.getTestConf();
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#search li")));
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
