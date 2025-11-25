package Base;

import Utilities.ExcelReader;
import Utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class TestBase {

    /*
    This class will have all the common methods and variables for the followings
     1. webdriver - done
     2. properties - done
     3. logs -
     4. extent report
     5. DB
     6. Email
     7. Excel
     8. ReportNG
     */


    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger logger = LogManager.getLogger("org.example");
    public static ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;

    public static ExcelReader excel;

    static {
        try {
            excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/Excel/Data.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeSuite
    public void setUp() {

        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Properties/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Properties/OR.properties");
                OR.load(fis);
            } catch (Exception e) {
                System.out.println("could not load properties file");
            }

            boolean headless = Boolean.parseBoolean(config.getProperty("headless"));

            if (config.getProperty("browser").equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-cookies"); // Disable cookies
                    options.addArguments("--disable-gpu"); // Disable GPU for headless mode
                    driver = new ChromeDriver(options);
                } else {
                    driver = new ChromeDriver();
                }
                logger.info("Chrome driver created");
            } else if (config.getProperty("browser").equals("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-cookies"); // Disable cookies
                    options.addArguments("--disable-gpu"); // Disable GPU for headless mode
                    driver = new FirefoxDriver(options);
                } else {
                    driver = new FirefoxDriver();
                }
                logger.info("Firefox driver created");
            } else if (config.getProperty("browser").equals("edge")) {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-cookies"); // Disable cookies
                    options.addArguments("--disable-gpu"); // Disable GPU for headless mode
                    driver = new EdgeDriver(options);
                } else {
                    driver = new EdgeDriver();
                }
                logger.info("Edge driver created");
            }
        }

        //getting url and maximizing
        driver.get(config.getProperty("testsiteurl"));
        logger.info("Navigated to " + config.getProperty("testsiteurl"));
        driver.manage().window().setPosition(new Point(-1000, 0));
        driver.manage().window().maximize();
        logger.info("Window maximized");


        //waits
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicitwait"))));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(config.getProperty("explicitwait"))));
        FluentWait<WebDriver> fluentWait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(Long.parseLong(config.getProperty("fluentwait"))))
                        .pollingEvery(Duration.ofSeconds(Long.parseLong(config.getProperty("fluentwait"))))
                        .ignoring(Exception.class);
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
//        Thread.sleep(15000);
        if (driver != null) {
            driver.quit();
        }
        logger.info("Driver closed");
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void Click(String locator) {
        if (locator.endsWith("_css")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_xpath")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_id")) {
            driver.findElement(By.id(OR.getProperty(locator))).click();
        }
        test.log(LogStatus.INFO, ("The locator '" + locator + "' is clicked"));
    }

    public void Type(String locator, String value) {
        if (locator.endsWith("_css")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_xpath")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
        } else if (locator.endsWith("_id")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
        }
        test.log(LogStatus.INFO, ("In the locator '" + locator + "' the value '" + value + "' is entered"));
    }

    public void SelectByVisibleText(String locator, String value) {
        WebElement dropdownElement = null;

        if (locator.endsWith("_css")) {
            dropdownElement = driver.findElement(By.cssSelector(OR.getProperty(locator)));
        } else if (locator.endsWith("_xpath")) {
            dropdownElement = driver.findElement(By.xpath(OR.getProperty(locator)));
        } else if (locator.endsWith("_id")) {
            dropdownElement = driver.findElement(By.id(OR.getProperty(locator)));
        }

        try {
            // Try as standard <select>
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(value);
            test.log(LogStatus.INFO, "Selected '" + value + "' from dropdown with locator: " + locator);
        } catch (UnexpectedTagNameException e) {
            // Handle custom dropdowns (div/li-based)
            dropdownElement.click(); // open the dropdown

            // Adjust below XPath based on your actual options structure
            String optionsXpath = "//li[normalize-space()='" + value + "']";
            List<WebElement> options = driver.findElements(By.xpath(optionsXpath));

            boolean found = false;
            for (WebElement option : options) {
                if (option.getText().equals(value)) {
                    option.click();
                    test.log(LogStatus.INFO, "Clicked custom dropdown option '" + value + "' for locator: " + locator);
                    found = true;
                    break;
                }
            }

            if (!found) {
                test.log(LogStatus.FAIL, "Option '" + value + "' not found in custom dropdown for locator: " + locator);
            }
        }
    }


    @DataProvider(name = "dp")
    public Object[][] getData(Method m) {
        String sheetname = m.getName();
        int rows = excel.getRowCount(sheetname);
        int cols = excel.getColumnCount(sheetname);
        Object[][] data = new Object[rows - 1][cols];

        for (int row = 1

             ; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                data[row - 1][col] = excel.getCellData(sheetname, row, col);
            }
        }
        return data;
    }


    public static boolean isTestRunnable(String testName, ExcelReader excel) {
        String sheetName = "test_suite";
        int rows = excel.getRowCount(sheetName);

        //need to check what is wrong in excel

        for (int rNum = 1; rNum <= rows; rNum++) {
            String testCase = excel.getCellData(sheetName, rNum, 0);
            if (testCase.equalsIgnoreCase(testName)) {
                String runMode = excel.getCellData(sheetName, rNum, 1);
                System.out.println("---------> "+runMode+" is the run mode for the test case "+testCase);
                if (runMode.equalsIgnoreCase("Y")) {
                    System.out.println("the test case "+testCase+" will be executed");
                    return true;
                }
            }
        }
     return false;
    }
}