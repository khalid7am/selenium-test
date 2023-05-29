import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class ProfilePage extends PageBase {
    private By pictureAvatar = By.xpath("//picture[contains(@class, 'user-header__avatar')]");
     
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String downloadFiles() {
        WebElement pictureElement = waitAndReturnElement(pictureAvatar);

        // Get the source elements within the picture element
        java.util.List<WebElement> sourceElements = pictureElement.findElements(By.tagName("source"));

        // Iterate through the source elements and retrieve the srcset attribute values
        for (WebElement sourceElement : sourceElements) {
            String srcSet = sourceElement.getAttribute("srcset");
            String type = sourceElement.getAttribute("type");
            
            System.out.println("srcset: " + srcSet);
            System.out.println("type: " + type);

            String destinationPath = System.getProperty("user.dir") + "/src/avatar." + type.replace("image/","");
            // should normally download the following types:
            // - image/webp
            // - image/png
            // - image/jpeg
            System.out.println("destinationPath: " + destinationPath);
            
            downloadImage(srcSet, destinationPath);

            // this.waitForSeconds(5000);
        }

        return "downloaded!";
    }

    private static void downloadImage(String imageUrl, String destinationPath) {
        try (InputStream in = new URL(imageUrl).openStream()) {
            Path destination = Path.of(destinationPath);
            Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}