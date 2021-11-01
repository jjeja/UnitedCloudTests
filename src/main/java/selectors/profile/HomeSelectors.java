package selectors.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeSelectors {
    private final WebDriver driver;

    public HomeSelectors(WebDriver driver) {
        this.driver = driver;
    }
    private static final String homePage = "https://qa-interview.united.cloud";
    private static final String profilesSelector = "profiles__profile";

    public String getHomePageUrl() {
        return homePage;
    }

    public List<WebElement> getProfiles() {
        return driver.findElements(By.className(profilesSelector));
    }

    public By getProfilesSelector() {
        return By.className(profilesSelector);
    }
}
