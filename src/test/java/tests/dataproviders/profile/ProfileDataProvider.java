package tests.dataproviders.profile;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class ProfileDataProvider {
    private static final int negativeYear = Faker.instance().number().numberBetween(-100, -20);
    private static final int futureYear = Faker.instance().number().numberBetween(2294, 2499);
    private static final int pastYear = Faker.instance().number().numberBetween(1389, 1813);
    private static final Integer emptyYear = null;

    private static final String nameOneChar = Faker.instance().lorem().characters(1);
    private static final String regularName = Faker.instance().name().firstName();
    private static final String alphaNumeric = "asf324234faf";
    private static final String specialCharacters = "!#?";

    @DataProvider(name = "birthYearProvider")
    public static Object[][] birthYearProvider() {
        return new Object[][] {
                {negativeYear},
                {futureYear},
                {pastYear},
                {emptyYear}
        };
    }

    @DataProvider(name = "nameDataProvider")
    public static Object[][] nameDataProvider() {
        return new Object[][]{
                {nameOneChar},
                {regularName},
                {alphaNumeric},
                {specialCharacters}
        };
    }
}
