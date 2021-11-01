package tests.profile;

import com.github.javafaker.Faker;
import methods.profile.CreateProfileMethods;
import methods.profile.DeleteProfileMethods;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import selectors.profile.DeleteProfileSelectors;
import selectors.profile.HomeSelectors;
import tests.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DeleteProfilesTests extends BaseTest {
    private final DeleteProfileMethods deleteProfileMethods;
    private final CreateProfileMethods createProfileMethods;
    private final DeleteProfileSelectors deleteProfileSelectors;
    private final HomeSelectors homeSelectors;
    private final Random random;

    public DeleteProfilesTests() {
        deleteProfileMethods = new DeleteProfileMethods(driver);
        createProfileMethods = new CreateProfileMethods(driver);
        deleteProfileSelectors = new DeleteProfileSelectors(driver);
        homeSelectors = new HomeSelectors(driver);
        this.random = new Random();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void deleteProfileTest() {
        String name = Faker.instance().lorem().characters(15);
        int age = random.nextInt(100);
        Integer birthYear = null;

        if (age >= 18) {
            birthYear = 1990;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);
        deleteProfileMethods.deleteProfile();

        webElementUtils.waitForElementToBeVisible(homeSelectors.getProfilesSelector());
        List<WebElement> profiles = homeSelectors.getProfiles();

        Assert.assertEquals(profiles.size(), 1);
    }

    @Test
    public void familyProfileDelete() {
        webElementUtils.waitForElementToBeVisible(homeSelectors.getProfilesSelector());
        List<WebElement> profiles = homeSelectors.getProfiles();
        profiles.get(0).click();

        String disabled = deleteProfileSelectors.getDeleteProfileButton().getAttribute("disabled");

        Assert.assertEquals(disabled, "true");
    }
}
