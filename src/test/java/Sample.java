import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Sample {
    public RemoteWebDriver dr;
    public static final String URL = "http://localhost:4444/wd/hub/";
    public static String testURL = "http://www.dogspecial.ru";

    static void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    @BeforeTest
    void start() throws MalformedURLException {
        this.dr = new RemoteWebDriver(new URL(URL), new ChromeOptions());
    }

    @Test
    void mainPageTest() {
        this.dr.get(testURL);
        sleep();
        WebElement element1 = this.dr.findElementByXPath("/html/body/div/div/header/div/div/div[1]/div/ul/li[1]/a");
        element1.click();
        sleep();
        WebElement element2 = this.dr.findElementByXPath("/html/body/div/div/div/main/div/div/section/ul/li[1]/div/div[4]/form/div/div/div[1]/input");
        sleep();
        element2.click();
        sleep();
        WebElement element3 = this.dr.findElementByXPath("/html/body/section/div[1]/div/div/div[2]/div/div/a");
        sleep();
        element3.click();
        sleep();
    }

    @AfterTest
    void close(){
        this.dr.close();
        this.dr.quit();
    }

}
