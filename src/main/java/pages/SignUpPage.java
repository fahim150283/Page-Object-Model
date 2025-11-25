package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }
    public void doSignUp(String username, String password) {
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#signup-termservice")).click();
        driver.findElement(By.cssSelector("#signupbtn")).click();
        System.out.println("Login Page Opened");
    }
    public void validateLoginPage() {

    }
}
