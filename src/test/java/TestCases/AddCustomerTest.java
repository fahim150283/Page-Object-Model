package TestCases;

import Base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProviderClass = TestBase.class, dataProvider = "dp")
    public void addCustomerTest(String firstName, String lastName, String postCode, String runmode) {

        if (!runmode.equalsIgnoreCase("Y")) {
            test.log(LogStatus.SKIP, "Runmode is NO for this user: " + firstName + " " + lastName);
            throw new SkipException("Run mode is NO");
        }

        test.log(LogStatus.INFO, "Provided Data: " + firstName + " " + lastName + " and " + postCode);
        // click on add customer button
        Click("addCustBtn_css");
        // enter first name
        Type("firstName_css", firstName);
        // enter last name
        Type("lastName_xpath", lastName);
        // enter post code
        Type("postCode_css", postCode);
        // click on add customer submit button
        Click("addCustSubmit_css");
        driver.switchTo().alert().accept();
    }


}
