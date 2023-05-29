import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.*;
import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;

public class FirstSeleniumTest {
    public WebDriver driver;
    
    @Before
    public void setup()  throws MalformedURLException  {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void websiteTest() {
        MainPage mainpage = new MainPage(this.driver);

        String homepageText = mainpage.getHomePageText();
        Assert.assertTrue(homepageText.contains("Popular"));
        Assert.assertTrue(mainpage.getFooterText().contains("Indie Hackers"));


        // Manipulate cookie
        CookieConsent cookieConsent = new CookieConsent(this.driver);
        cookieConsent.manipulateCookieConsent();
        
        
        // Test login
        LoginPage loginpage = new LoginPage(this.driver);

        loginpage.openLogin();
        String loginLink = loginpage.getLoginUrl();
        Assert.assertEquals(loginLink, "https://www.indiehackers.com/sign-in");
        String result = loginpage.login("sclxhe@inf.elte.hu", "Password123!");
        Assert.assertTrue(result.equals("success!"));


        // Search for 'javascript' topics, apply filter and pagination..
        SearchResultPage searchpage = new SearchResultPage(this.driver);

        searchpage.openSearchPage();
        String results = searchpage.getSearchResults();
        Assert.assertTrue(results.equals("success!"));


        // Edit profile (change information)
        EditProfile editprofile = new EditProfile(this.driver);

        editprofile.goToEditPage();
        String updateInformation = editprofile.updateProfile();  
        Assert.assertTrue(updateInformation.equals("updated!"));


        // Download files
        ProfilePage profilepage = new ProfilePage(this.driver);

        String downloadAvatar = profilepage.downloadFiles();
        Assert.assertTrue(downloadAvatar.equals("downloaded!"));


        // History test (browser back button)
        mainpage.goToPreviousPage();


        // Multiple page test from array
        mainpage.multiplePageTest();


        // Log out
        Logout logOut = new Logout(this.driver);
        logOut.logout();
        String lougoutlink = logOut.lougoutLink();
        Assert.assertTrue(lougoutlink.equals("https://www.indiehackers.com/"));
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
