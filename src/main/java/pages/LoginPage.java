package pages;

import Base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public void doLogin(String username, String password) {
        driver.findElement(By.cssSelector("#login_id")).clear();
        driver.findElement(By.cssSelector("#login_id")).sendKeys(username);
        driver.findElement(By.cssSelector("#nextbtn")).click();
        System.out.println("Login Page Opened and Login Button Clicked");
    }
    public void validateLoginPage() {

    }
}
