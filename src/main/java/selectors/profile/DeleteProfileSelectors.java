package selectors.profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DeleteProfileSelectors {

    public static final String deleteProfileButtonSelector = ".card__delete";

    private final WebDriver driver;

    public DeleteProfileSelectors(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getDeleteProfileButton() {
        return driver.findElement(By.cssSelector(deleteProfileButtonSelector));
    }
}
