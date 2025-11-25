package Utilities;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.net.URL;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Report output path
            String reportPath = System.getProperty("user.dir") + "/target/surefire-reports/html/ExtentReport.html";

            extent = new ExtentReports(reportPath, true);

            try {
                // Try to load extent-config.xml from resources
                ClassLoader classLoader = ExtentManager.class.getClassLoader();
                URL configFileUrl = classLoader.getResource("extent-config.xml");

                if (configFileUrl != null) {
                    extent.loadConfig(new File(configFileUrl.getFile()));
                    System.out.println("✅ Loaded extent-config.xml successfully.");
                } else {
                    System.out.println("⚠️ extent-config.xml not found. Proceeding with default ExtentReport settings.");
                }
            } catch (Exception e) {
                System.out.println("❌ Failed to load extent-config.xml. Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return extent;
    }
}
