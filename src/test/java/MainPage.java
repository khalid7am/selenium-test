import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

class MainPage extends PageBase {
    private By homepage = By.xpath("//div[contains(@class, 'homepage')]");
    private By footerBy = By.className("site-footer");
    private By groupIndex = By.xpath("//div[contains(@class, 'group-index')]");

    // Array of page URLs
    String[] pageUrls = {
        "https://www.indiehackers.com/group/looking-to-partner-up",
        "https://www.indiehackers.com/group/ideas-and-validation",
        "https://www.indiehackers.com/group/developers",
        "https://www.indiehackers.com/group/growth",
    };
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.indiehackers.com/");
    }

    public String getHomePageText() {         
        return this.waitAndReturnElement(homepage).getText();
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    public void multiplePageTest() {
        for (String url : pageUrls) {
            this.driver.get(url);

            WebElement groupElement = waitAndReturnElement(groupIndex);
            this.wait.until(ExpectedConditions.visibilityOf(groupElement));

            Assert.assertTrue(groupElement.getText().contains("Popular"));
        }
    }

    public void goToPreviousPage() {
        this.driver.navigate().back();
        System.out.println("Back to URL: " + this.driver.getCurrentUrl());
    }
}
