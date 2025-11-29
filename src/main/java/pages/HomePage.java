package pages;

import Base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends Page {

    public void goToSignup() {
        driver.findElement(By.cssSelector("#header > div.zgh-utilities > div.zgh-accounts > a.zgh-signup")).click();
    }
    public void goToLogin() {
        driver.findElement(By.cssSelector("#header > div.zgh-utilities > div.zgh-accounts > a.zgh-login")).click();
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
