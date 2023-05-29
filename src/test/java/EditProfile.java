import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import java.awt.event.KeyEvent;
import java.awt.*;

class EditProfile extends PageBase {
    private By notificationsCheckbox = By.xpath("//label[contains(@class, 'user-settings__labeled-checkbox')]");
    private By editButtonIcon = By.xpath("//div[contains(@class, 'header-button__content')]");
    
    private By fullnameInput = By.xpath("//input[contains(@class, 'edit-form__field')]");
    // private By profileImageInput = By.xpath("//div[contains(@class, 'user-header__avatar-wrapper--editable')]");
    // private By profileInput = By.xpath("//input[contains(@class, 'avatar')]");
    private By monthDropdown = By.xpath("//div[contains(@class, 'edit-form__field--birthday-month')]");
    private By monthDropdownOptions = By.xpath("//div[contains(@class, 'edit-form__field--birthday-month')]/div[2]/div/div[5]");
    private By dayDropdown = By.xpath("//div[contains(@class, 'edit-form__field--birthday-day')]");
    private By dayDropdownOptions = By.xpath("//div[contains(@class, 'edit-form__field--birthday-day')]/div[2]/div/div[9]");
    private By yearDropdown = By.xpath("//div[contains(@class, 'edit-form__field--birthday-year')]");
    private By yearDropdownOptions = By.xpath("//div[contains(@class, 'edit-form__field--birthday-year')]/div[2]/div/div[12]");
    
    private By saveButton = By.xpath("//button[contains(@class, 'edit-form__button--save')]");

    private By profileHoverUserImageLocator = By.xpath("//div[contains(@class, 'title-bar__user-link')]");
    private By profileHoverSettingsLocator = By.xpath("//a[contains(@class, 'user-menu__action')][5]");
    
    public EditProfile(WebDriver driver) {
        super(driver);
    }

    public void goToEditPage() {
        WebElement we = waitAndReturnElement(profileHoverUserImageLocator);
        this.wait.until(ExpectedConditions.visibilityOf(we));
        this.action.moveToElement(we).perform();
        waitAndReturnElement(profileHoverSettingsLocator).click();
        
        this.waitForSeconds(2000);
    }

    public String updateProfile() {
        // 1. uncheck email notifications
        waitAndReturnElement(notificationsCheckbox).click();
        this.waitForSeconds(4000);
        
        // 2. click on edit icon (pen)
        waitAndReturnElement(editButtonIcon).click();
        this.waitForSeconds(2000);

        // 3. upload profile image
        // waitAndReturnElement(profileImageInput).click();
        // String path = System.getProperty("user.dir") + "\\src\\image.jpeg";
        // waitAndReturnElement(profileInput).sendKeys(path);

        // 4. write full name
        waitAndReturnElement(fullnameInput).clear();
        waitAndReturnElement(fullnameInput).sendKeys("Khalid Hamdani");
    
        // 5. select birthday date
        waitAndReturnElement(monthDropdown).click();
        WebElement md = waitAndReturnElement(monthDropdown);
        this.action.moveToElement(md).sendKeys(Keys.DELETE).perform();
        waitAndReturnElement(monthDropdownOptions).click();

        waitAndReturnElement(dayDropdown).click();
        WebElement dd = waitAndReturnElement(dayDropdown);
        this.action.moveToElement(dd).sendKeys(Keys.DELETE).perform();
        waitAndReturnElement(dayDropdownOptions).click();

        waitAndReturnElement(yearDropdown).click();
        WebElement yd = waitAndReturnElement(yearDropdown);
        this.action.moveToElement(yd).sendKeys(Keys.DELETE).perform();
        waitAndReturnElement(yearDropdownOptions).click();

        // 6. save changes
        waitAndReturnElement(saveButton).click();
        this.waitForSeconds(4000);

        return "updated!";
    }
}