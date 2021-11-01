package methods.profile;

import org.openqa.selenium.WebDriver;
import selectors.profile.HomeSelectors;

public class HomeMethods {
    private final WebDriver driver;
    private final HomeSelectors chooseProfileSelectors;

    public HomeMethods(WebDriver driver) {
        this.driver = driver;
        chooseProfileSelectors = new HomeSelectors(driver);
    }

    public void goToChooseProfile() {
        driver.get(chooseProfileSelectors.getHomePageUrl() + "/choose-profile");
    }
}
