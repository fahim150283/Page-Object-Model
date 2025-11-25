package TestCases;

import Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class OpenAccountTest extends TestBase {

    @Test(dataProviderClass = TestBase.class, dataProvider = "dp")
    public void openAccountTest(String customer, String currency) {

        test.log(LogStatus.INFO, "Provided Data: " + customer + " and " + currency);

        // click on open account button
        Click("openAccBtn_xpath");

        // now i shall click on customer drop down that matches the name from the data provider
//        Click("custDropDown_css");

        // now i shall click on the customer that matches the name from the data provider
        SelectByVisibleText("custSelector_id", customer);

        // now i shall click on currency drop down that matches the name from the data provider
//        Click("currencyDropDown_xpath");

        SelectByVisibleText("currencySelector_id", currency);


        // click on process button
        Click("processBtn_css");
        test.log(LogStatus.INFO, ("The locator '" + "processBtn_xpath" + "' is clicked"));

        driver.switchTo().alert().accept();
    }
}
