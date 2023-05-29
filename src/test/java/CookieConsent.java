import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

class CookieConsent extends PageBase {
    public CookieConsent(WebDriver driver) {
        super(driver);
    }

    public void manipulateCookieConsent() {
        // Create a new cookie
        Cookie consentCookie = new Cookie("consent", "true", ".indiehackers.com", "/", null, false, false);

        // Add the cookie to the current domain
        this.driver.manage().addCookie(consentCookie);

        // Refresh the page to apply the cookie
        this.driver.navigate().refresh();

        this.waitForSeconds(2000);

        System.out.println("Cookie manipulated!");
    }
}