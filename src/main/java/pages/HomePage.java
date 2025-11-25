package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {


    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSignup() {
        driver.findElement(By.cssSelector("#header > div.zgh-utilities > div.zgh-accounts > a.zgh-signup")).click();
    }
    public void goToLogin() {
        driver.findElement(By.cssSelector("#header > div.zgh-utilities > div.zgh-accounts > a.zgh-login")).click();
        System.out.println("Login Page Opened");
    }
    public void goToSupport() {
        driver.findElement(By.cssSelector("a.zh-btn.zwc-blue-bt.ccodeall")).click();
    }




//    Do it the same way for these

//    public void goToZohoEdu() {
//
//    }
//    public void goToLearnMore() {
//
//    }
//    public void validateFooterLinks() {
//
//    }
}
