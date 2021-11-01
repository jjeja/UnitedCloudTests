package tests.profile;

import com.github.javafaker.Faker;
import methods.profile.CreateProfileMethods;
import methods.login.LogInMethods;
import methods.profile.HomeMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CreateProfileTests extends BaseTest {

    private static final String KIDS = "KIDS";
    private static final String TEEN = "TEEN";
    private static final String ADULT = "ADULT";

    private final LogInMethods logInMethods;
    private final HomeMethods homeMethods;
    private final CreateProfileMethods createProfileMethods;
    public final String username = "jelena.jankovic";
    public final String password = "Lozinka123";
    public final Random random;


    public CreateProfileTests() {
        logInMethods = new LogInMethods(driver);
        homeMethods = new HomeMethods(driver);
        createProfileMethods = new CreateProfileMethods(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        random = new Random();

    }

    @BeforeTest
    public void setup() {
        this.logInMethods.submitLogIn(username, password);
    }

    @AfterMethod
    public void cleanup() {
        homeMethods.goToChooseProfile();

        while(true) {
            List<WebElement> profiles = driver.findElements(By.className("profiles__profile"));
            if (profiles.size() <= 1) {
                break;
            }

            profiles.get(1).click();
            driver.findElement(By.className("card__delete")).click();
        }
    }

    @Test
    public void createProfileTest() {
        int age = random.nextInt(100);
        Integer birthYear = null;
        String name = Faker.instance().name().firstName();

        if (age >= 18) {
            birthYear = 1990;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);

        String createdProfileName = driver.findElement(By.className("card__profile-name"))
                .getAttribute("innerText");
        String createdProfileType = driver.findElement(By.className("card__profile-type"))
                .getAttribute("innerText");

        Assert.assertEquals(createdProfileName, name);
        if (age <= 11) {
            Assert.assertEquals(createdProfileType, KIDS);
        } else if (age <= 17) {
            Assert.assertEquals(createdProfileType, TEEN);
        } else {
            Assert.assertEquals(createdProfileType, ADULT);
        }
    }

    @Test
    public void nameOneCharacterTest() {
        String name = Faker.instance().lorem().characters(1);
        int age = random.nextInt(100);
        Integer birthYear = null;

        if (age >= 18) {
            birthYear = 1990;
        }

        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);

        String createdProfileName = driver.findElement(By.className("card__profile-name"))
                .getAttribute("innerText");

        Assert.assertEquals(createdProfileName, name);
    }

    @Test
    public void nameLengthTest() {
        int age = random.nextInt(100);
        Integer birthYear = null;

        if (age >= 18) {
            birthYear = 1990;
        }

        // how to assert that note
        String name = Faker.instance().lorem().characters(40);
        createProfileMethods.createProfileFromChooseMenu(name, age, birthYear);

        String createdProfileName = driver.findElement(By.className("card__profile-name"))
                .getAttribute("innerText");

        Assert.assertEquals(createdProfileName, name.substring(0, 32));
    }

    @Test
    public void ageTest() {
        int age = 22;
        int birthYear = Calendar.getInstance().get(Calendar.YEAR) + 10;

        createProfileMethods.createProfileFromChooseMenu("testAge", age, birthYear);
        //Assert.assertEquals("error", "Age cannot be in the future");

        birthYear = 1732;
        createProfileMethods.createProfileFromNavigation("testAge", age, birthYear);
        //Assert.assertEquals("error", "Age is more than 150 years in the past");
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
