package Tests.US_18;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider()
    public static Object[][] couponsPositiveData() {

        return new Object[][]{
                //positive test

                //After filling in the required fields to create a coupon, the coupon can be created.
                //(Positive Scenario)"
                {"karne05","karnesini alan herkes bu kodla %10 indirim kazanır","10","2026-08-15"}

        };
    }

    @DataProvider()
    public static Object[][] couponsNegativeData() {
        return new Object[][]{
                //Negative test

                //"Coupon cannot be created by entering a past date.(Negative Scenario)"
                {"kartanesi","kış indirimi","200","1925-04-09"}

                //"If the Code TextBox is left blank, no coupon should be created.(Negative Scenario)"
                ,{"","kış indirimi","1000","2026-08-09"}

                //"If the Coupon Amount Textbox is left blank, the coupon should not be created.(Negative Scenario)"
                ,{"ilkbahar","ilkbahar indirimi","","2026-08-09"}

                //"If the Coupon expiry date textbox is left blank, the coupon should not be created.(Negative Scenario)"
                ,{"sonbahar00","sonbahar indirimi","5",""}

                //"Coupon cannot be created when numbers that do not specify a date are entered in the Coupon expiry date textbox.(Negative Scenario)"
                ,{"kutulu30","kutu oyunu indirimi indirimi","25","1234567"}

        };
    }
}
