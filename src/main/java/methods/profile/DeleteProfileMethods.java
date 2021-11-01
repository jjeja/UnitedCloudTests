package methods.profile;

import org.openqa.selenium.WebDriver;
import selectors.profile.DeleteProfileSelectors;

public class DeleteProfileMethods {
    private final WebDriver driver;
    private final DeleteProfileSelectors selectors;

    public DeleteProfileMethods(WebDriver driver) {
        this.driver = driver;
        this.selectors = new DeleteProfileSelectors(driver);
    }

    public void deleteProfile() {
        selectors.getDeleteProfileButton().click();
    }
}
