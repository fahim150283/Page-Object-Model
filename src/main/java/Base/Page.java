package Base;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Page {
    public WebDriver driver;
    public Page() {
        driver = new ChromeDriver();
        driver.get("https://zoho.com");
        driver.manage().window().setPosition(new Point(-1000, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
