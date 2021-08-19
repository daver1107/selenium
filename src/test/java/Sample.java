import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Sample {
    private static final String cssMenuElement = "#s-header-catalog > ul > li:nth-child(1) > a";
    private static final String xpathAddToCartElement = "/html/body/div/div/div/main/div/div/section/ul/li[1]/div/div[4]/form/div/div/div[1]/input";
    private static final String xpathGoToCartElement = "/html/body/section/div[1]/div/div/div[2]/div/div/a";
    private static final String xpathChekOutElement = "//input[@name= \"checkout\"]";
    private RemoteWebDriver dr;
    private static final String URL = "http://localhost:4444/wd/hub/";
    private static final String testURL = "http://www.dogspecial.ru";

    static void sleep() {
        try {
            Thread.sleep(600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    void start() throws MalformedURLException {
        dr = new RemoteWebDriver(new URL(URL), new ChromeOptions());
    }

    @Test
    void addToCart() {
        dr.get(testURL);
        sleep();
        dr.findElementByCssSelector(cssMenuElement).click();
        sleep();
        dr.findElementByXPath(xpathAddToCartElement).click();
        sleep();
        dr.findElementByXPath(xpathGoToCartElement).click();
        sleep();
        dr.findElementByXPath(xpathChekOutElement).click();

        sleep();
        //Кэшируем поля формы оформления заказа
        WebElement firstName = dr.findElementByXPath("//input [@name = \"customer[firstname]\"]");
        WebElement lastName = dr.findElementByXPath("//input [@name = \"customer[lastname]\"]");
        WebElement phone = dr.findElementByXPath("//input [@name = \"customer[phone]\"]");
        WebElement email = dr.findElementByXPath("//input [@name = \"customer[email]\"]");
        WebElement street = dr.findElementByXPath("//input [@name = \"customer[address.shipping][street]\"]");
        WebElement city = dr.findElementByXPath("//input [@name = \"customer[address.shipping][city]\"]");
        WebElement region = dr.findElementByXPath("//*/select/option[43]");
        WebElement zip = dr.findElementByXPath("//input [@name = \"customer[address.shipping][zip]\"]");
        //Заполняем поля данными
        firstName.click();
        firstName.sendKeys("Ivan");
        lastName.click();
        lastName.sendKeys("Ivanov");
        phone.click();
        phone.sendKeys("4951234567");
        email.click();
        email.sendKeys("ivan@ivanov.com");
        street.click();
        street.sendKeys("123 Main street");
        city.click();
        city.sendKeys("Moscow");
        region.click();
        sleep();
        region.click();
        sleep();
        zip.click();
        zip.sendKeys("12345");
        sleep();
        //Отправляем заказ
        dr.findElementByXPath("//input [@class= \"s-step-submit\"]").click();
        sleep();

    }

//    @AfterTest
//    void close(){
//        dr.close();
//        dr.quit();
//    }

}
