import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

class Logout extends PageBase {
    private By profileHoverUserImageLocator = By.xpath("//div[contains(@class, 'title-bar__user-link')]");
    private By profileHoverLogoutLocator = By.xpath("//div[contains(@class, 'user-menu__action')]");

    public Logout(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        WebElement we = waitAndReturnElement(profileHoverUserImageLocator);
        this.wait.until(ExpectedConditions.visibilityOf(we));
        this.action.moveToElement(we).perform();
        waitAndReturnElement(profileHoverLogoutLocator).click();
        
        System.out.println("logged Out");
        this.waitForSeconds(2000);
    }

    public String lougoutLink() {
        String expectedUrl = this.driver.getCurrentUrl();
        System.out.println(expectedUrl);
        return expectedUrl;
    }
}