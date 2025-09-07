package Tests.US_10;

import org.testng.annotations.DataProvider;

public class DataProvidersUS10 {

    DataProvidersUS10 dataProviders;

    @DataProvider(name = "GOOD")
    public static Object[][] goodDatalar() {
        return new Object[][]{
                {"Emrem*"}, {"12345,,"}, {"**tech"}, {"Tech_."}, {"QA57.,"}, {"!,.-#/"}
        };
    }


    @DataProvider(name = "STRONG")
    public static Object[][] strongDatalar() {
        return new Object[][]{
                {"Emrem**"}, {"12345a!!"}, {"123tech*"}, {"Tech_!"}, {"QA57.##"}, {"!,.-#/**"}
        };

    }


    @DataProvider(name = "TOOSHORT")
    public static Object[][] tooShortDatalar() {
        return new Object[][]{
                {"Emrem"}, {"12345"}, {"**tec"}, {"Tech_"}, {"QA57."}, {"!,.-#"}
        };
    }

    @DataProvider(name = "WEAK")
    public static Object[][] weakDatalar() {
        return new Object[][]{
                {"Emrem."},{"123456"},{"*tech."},{"12345a"},{"QA57.."},{"!/////"}
        };

    }
}