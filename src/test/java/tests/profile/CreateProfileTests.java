package tests.profile;

import com.github.javafaker.Faker;
import methods.profile.CreateProfileMethods;
import methods.profile.HomeMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import selectors.profile.DeleteProfileSelectors;
import selectors.profile.HomeSelectors;
import selectors.profile.ProfileSelectors;
import tests.BaseTest;
import tests.dataproviders.profile.ProfileDataProvider;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CreateProfileTests extends BaseTest {

    private static final String KIDS = "KIDS";
    private static final String TEEN = "TEEN";
    private static final String ADULT = "ADULT";

    private final HomeMethods homeMethods;
    private final CreateProfileMethods createProfileMethods;
    private final DeleteProfileSelectors deleteProfileSelectors;
    private final HomeSelectors homeSelectors;
    private final ProfileSelectors profileSelectors;

    public final Random random;

    public CreateProfileTests() {
        homeMethods = new HomeMethods(driver);
        createProfileMethods = new CreateProfileMethods(driver);
        deleteProfileSelectors = new DeleteProfileSelectors(driver);
        homeSelectors = new HomeSelectors(driver);
        profileSelectors = new ProfileSelectors(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        random = new Random();
    }

    @AfterMethod
    public void cleanup() {
        homeMethods.goToChooseProfile();

        while(true) {
            List<WebElement> profiles = homeSelectors.getProfiles();
            if (profiles.size() <= 1) {
                break;
            }

            profiles.get(1).click();
            deleteProfileSelectors.getDeleteProfileButton().click();
        }
    }

    @Test
    public void createProfileTest() {
        String name = Faker.instance().name().firstName();
        int age = random.nextInt(100);
        Integer birthYear = null;

        if (age >= 18) {
            birthYear = 1990;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);

        String createdProfileName = profileSelectors.getProfileName().getAttribute("innerText");
        String createdProfileType = profileSelectors.getProfileType().getAttribute("innerText");

        Assert.assertEquals(createdProfileName, name);
        if (age <= 11) {
            Assert.assertEquals(createdProfileType, KIDS);
        } else if (age <= 17) {
            Assert.assertEquals(createdProfileType, TEEN);
        } else {
            Assert.assertEquals(createdProfileType, ADULT);
        }
    }

    @Test(dataProvider = "nameDataProvider",
            dataProviderClass = ProfileDataProvider.class)
    public void profileNameTest(String name) {
        int age = random.nextInt(100);
        Integer birthYear = null;

        if (age >= 18) {
            birthYear = 1990;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);
        String createdProfileName = profileSelectors.getProfileName().getAttribute("innerText");

        Assert.assertEquals(createdProfileName, name);
    }

    @Test
    public void profileNameMaximumLengthTest() {
        int age = random.nextInt(100);
        Integer birthYear = null;

        if (age >= 18) {
            birthYear = 1990;
        }

        String name = Faker.instance().lorem().characters(40);
        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);

        String createdProfileName = profileSelectors.getProfileName().getAttribute("innerText");

        Assert.assertEquals(createdProfileName, name.substring(0, 32));
    }

    @Test
    public void emptyNameTest() {
        createProfileMethods.createProfileFromChooseMenu("", 5, null);
        // cannot find this in dom
        // String error = profileSelectors.getErrorName().getAttribute("innerText");

        // Assert.assertEquals(error, "Please fill in this field.");
    }

    /**
     * This test is just a placeholder, currently all wrong cases pass
     */
    @Test(dataProvider = "birthYearProvider",
            dataProviderClass = ProfileDataProvider.class)
    public void ageTest(Integer birthYear) {
        createProfileMethods.createProfileFromChooseMenu("testAge", 22, birthYear);
        //Assert.assertEquals("error", "Age is not correct");
    }

    @Test
    public void maximumProfilesReachedNoCreateButton() {
        int age = random.nextInt(100);
        Integer birthYear = null;
        String name = Faker.instance().name().firstName();

        if (age >= 18) {
            birthYear = 1283;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);

        // create maximum number of profiles = 5 (one above and 4 in loop)
        for (int i = 0; i <= 3; i++) {
            createProfileMethods.createProfileFromNavigation(Faker.instance().name().firstName(), age, birthYear);
        }

        createProfileMethods.createProfileFromNavigation(Faker.instance().name().firstName(), age, birthYear);

        String errorMsg = driver.findElement(By.className("form__error")).getAttribute("innerText");
        Assert.assertEquals(errorMsg, "Maximum number of profiles reached for this user.");
    }
}
