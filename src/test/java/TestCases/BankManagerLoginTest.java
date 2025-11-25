package TestCases;

import Base.TestBase;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends TestBase {

    @Test(priority = -1)
    public void bankManagerLoginTest() {
        logger.info("Login test started");
        try {
            Click("bmlBtn_css");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
