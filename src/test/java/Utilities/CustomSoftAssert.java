package Utilities;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class CustomSoftAssert {
    private SoftAssert softAssert;
    private WebDriver driver;
    private com.relevantcodes.extentreports.ExtentTest test;

    public CustomSoftAssert(WebDriver driver, com.relevantcodes.extentreports.ExtentTest test) {
        this.softAssert = new SoftAssert();
        this.driver = driver;
        this.test = test;
    }

    public void assertTrue(boolean condition, String message) {
        if (!condition) {
            String screenshotName = message + ".png";
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, screenshotName);
            test.log(LogStatus.FAIL, message + test.addScreenCapture(screenshotName));
        } else {
            test.log(LogStatus.PASS, message);
        }
        softAssert.assertTrue(condition, message);
    }

    public void assertEquals(Object actual, Object expected, String message) {
        if (!actual.equals(expected)) {
            String screenshotName = message + ".png";
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, screenshotName);
            test.log(LogStatus.FAIL, message + " | Expected: " + expected + " but got: " + actual + test.addScreenCapture(screenshotName));
        } else {
            test.log(LogStatus.PASS, message);
        }
        softAssert.assertEquals(actual, expected, message);
    }

    public void assertAll() {
        softAssert.assertAll();
    }

}
