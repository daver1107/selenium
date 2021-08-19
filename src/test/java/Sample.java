import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class Sample {
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
        //Переходим на страницу
        dr.get(testURL);
        sleep();
        //Пошагово добавляем товар в корзину
        dr.findElementByXPath("//*[.='Апортировочные предметы']").click();
        sleep();
        dr.findElementByXPath("//*[@class = \"s-button js-add-button\"]").click();
        sleep();
        dr.findElementByXPath("//*[@id=\"js-cart-wrapper\"]/a").click();
        sleep();
        dr.findElementByXPath("//input[@name= \"checkout\"]").click();
        sleep();
        //Кэшируем поля формы оформления заказа
        WebElement firstName = dr.findElementByXPath("//input [@name = \"customer[firstname]\"]");
        WebElement lastName = dr.findElementByXPath("//input [@name = \"customer[lastname]\"]");
        WebElement phone = dr.findElementByXPath("//input [@name = \"customer[phone]\"]");
        WebElement email = dr.findElementByXPath("//input [@name = \"customer[email]\"]");
        WebElement street = dr.findElementByXPath("//input [@name = \"customer[address.shipping][street]\"]");
        WebElement city = dr.findElementByXPath("//input [@name = \"customer[address.shipping][city]\"]");
        WebElement zip = dr.findElementByXPath("//input [@name = \"customer[address.shipping][zip]\"]");
        dr.findElementByXPath("//*/select/option[43]").click(); //не кэшируем, ввод данных не требуется
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
        sleep();
        zip.click();
        zip.sendKeys("12345");
        sleep();
        //Отправляем заказ
        dr.findElementByXPath("//input [@class= \"s-step-submit\"]").click();
        sleep();
    }

    @AfterTest
    void close(){
        dr.close();
        dr.quit();
    }

}
