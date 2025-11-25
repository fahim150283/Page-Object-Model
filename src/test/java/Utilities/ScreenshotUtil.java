package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName;
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            Reporter.log("Screenshot captured at: " + path);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}