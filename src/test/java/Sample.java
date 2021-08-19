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
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    void start() throws MalformedURLException {
        this.dr = new RemoteWebDriver(new URL(URL), new ChromeOptions());
    }

    @Test
    void addToCart() {
        dr.get(testURL);
        sleep();
        dr.findElementByCssSelector("#s-header-catalog > ul > li:nth-child(1) > a").click();
        sleep();
        dr.findElementByXPath("/html/body/div/div/div/main/div/div/section/ul/li[1]/div/div[4]/form/div/div/div[1]/input").click();
        sleep();
        dr.findElementByXPath("/html/body/section/div[1]/div/div/div[2]/div/div/a").click();
        sleep();

    }

    @AfterTest
    void close(){
        dr.close();
        dr.quit();
    }

}
