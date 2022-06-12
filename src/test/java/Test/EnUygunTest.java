package Test;

import com.enuygun.base.BaseTest;
import com.enuygun.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class EnUygunTest extends BaseTest {



    @Test(groups = {"e2e"},description = "A Test that searches flights with desired options")
    public void searchFlightWithDesiredOptions(){

       HomePage homePage =  new HomePage(getDriver())
                .enterDeparture("SAW")
                .enterDestination("ADB")
                .enterDepartureDate("16 Eylül 2022")
                .enterReturnDate("10 Kasım 2022")
                .clickNonStop()
                .selectPassengerAndCount("65 yaş üstü",2)
                .selectPassengerAndCount("Yetişkin",2)
                .selectPassengerAndCount("Öğrenci",2)
                .clickClassType("Business")
                .clickFindFlightButton();

       Assert.assertEquals(homePage.returnHeader(),"İstanbul İzmir | 16 Eyl 2022, Cum - 10 Kas 2022, Per | 6 Yolcu | Business");

    }

}
