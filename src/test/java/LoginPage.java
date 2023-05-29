import org.openqa.selenium.interactions.Actions;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;
import java.awt.event.KeyEvent;
import java.awt.*;

class LoginPage extends PageBase {
    private By authButton = By.className("title-bar__auth-button--log-in");
    private By emailInput = By.className("pw-sign-in__field--email");
    private By passwordInput = By.xpath("//input[contains(@class, 'pw-sign-in__field--password')]");
    private By loginButton = By.xpath("//button[contains(@class, 'pw-sign-in__button')]");

    public LoginPage(WebDriver driver ){
        super(driver);
    }

    public void openLogin() {
        this.waitAndReturnElement(authButton).click();
        this.waitForSeconds(1000);
    }

    public String getLoginUrl() {         
        String LoginUrl = this.driver.getCurrentUrl();
        return LoginUrl;
    }

    public String login(String userID, String password){
        this.waitAndReturnElement(emailInput).sendKeys(userID);
        this.waitAndReturnElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).sendKeys(Keys.ENTER);

        return "success!";
    }
}