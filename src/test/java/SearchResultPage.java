import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;
import java.awt.event.KeyEvent;
import java.awt.*;

class SearchResultPage extends PageBase {
    private By searchButton = By.xpath("//div[contains(@class, 'title-bar__right')]/a");

    private By searchLocator = By.xpath("//div//input[contains(@class, 'search-page__field')]");
    private By searchBodyLocator = By.xpath("//div[contains(@class, 'search-page')]");
    private By searchHeaderLocator = By.xpath("//div[contains(@class, 'search-page__results-header')]");
    private By searchContentLocator = By.xpath("//div[contains(@class, 'search-page__results-content')]");
    
    private By searchFilterDiscussionsLocator = By.xpath("//ul[contains(@class, 'search-page__filters')]/li[2]");
    private By searchFilterDropdownLocator = By.xpath("//div[contains(@class, 'dropdown-menu__toggle')]");
    private By searchFilterDropdownMostUpvotesLocator = By.xpath("//div[contains(@class, 'dropdown-menu__items')]/div[2]");
    private By searchFilterPaginationLocator = By.xpath("//*[contains(@class, 'results-pagination__button--next')]");
    
    private By searchHoverUserImageLocator = By.xpath("//div[contains(@class, 'result__user-link')][1]");
    private By searchHoverUsernameLocator = By.xpath("//div[contains(@class, 'result__user-link')]/div/a[1]");
    
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void openSearchPage() {
        this.waitAndReturnElement(searchButton).click();
        this.waitForSeconds(2000);
    }

    public String getSearchResults() {
        //1. open search page
        WebElement searchBarElement = this.waitAndReturnElement(searchLocator);

        //2. type 'javascript'
        searchBarElement.sendKeys("javascript");

        WebElement headerSearchPage = waitAndReturnElement(searchHeaderLocator);
        String headerText = headerSearchPage.getText();
        Assert.assertTrue(headerText.contains("Discussions"));
        Assert.assertTrue(headerText.contains("Best Match"));

        //3. check if posts showed up containing 'javascript' in the title
        String searchContentText = waitAndReturnElement(searchContentLocator).getText();
        Assert.assertTrue(searchContentText.contains("javascript"));

        waitAndReturnElement(searchFilterDiscussionsLocator).click();

        headerText = headerSearchPage.getText();
        Assert.assertTrue(headerText.contains("results"));

        //4. filter by most upvote
        waitAndReturnElement(searchFilterDropdownLocator).click();
        waitAndReturnElement(searchFilterDropdownMostUpvotesLocator).click();

        //5. go to second page(pagination)
        waitAndReturnElement(searchFilterPaginationLocator).click();
        this.waitForSeconds(2000);
        headerText = headerSearchPage.getText();
        Assert.assertTrue(headerText.contains("Most Upvotes"));
        System.out.println(headerText);
        Assert.assertTrue(headerText.contains("20"));

        //6. hover over some publisher icon to see his profile
        WebElement we = waitAndReturnElement(searchHoverUserImageLocator);
        // Wait until the hover element is visible or present
        this.wait.until(ExpectedConditions.visibilityOf(we));
        this.action.moveToElement(we).perform();
        
        waitAndReturnElement(searchHoverUsernameLocator).click();

        // wait for redirect to profile page
        this.waitForSeconds(5000);

        //7. check page title
        String pageTitle = this.driver.getTitle();
        System.out.println("Page Title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("on Indie Hackers"));

        return "success!";
    }
}
