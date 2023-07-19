package tests.day24_Priority_DependsOnMethods_SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C04_DependsOnMethods {
    WebDriver driver;
    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

    @BeforeClass //her class'tan once bir kere calisir
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    //
    @Test
    public void test01() { //beforeclass bir kez calisti ve sonra test01 calisti ve ona bagli olan test02 de calisti. Baslangicta beforeclass yerine beforemethod yazmistik
        //o sekilde ikinci testimiz calisamadi. BeforeMethod kullanirsak ayni JUnit'deki before gibi her testten once calisir. Dolayisiyla test01'de once beforemethod sonra
        //test calisti ve amazon sayfasina gitti. BeforeMethod test02'den once tekrar calisinca bos bir driver aciyor ve buradan amazon'a gitmedigi icin aramakutusunu
        //bulamayip arama yaptiramiyor ve hata veriyor. Ancak beforeclass yapinca class basinda bir kere calistigi icin test01'i calistiriyor, sonra da bagli oldugu testi
        //calistiriyor.
        driver.get("https://amazon.com");
    }

    @Test(dependsOnMethods = "test01")
    public void test02() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone", Keys.ENTER);
    }
    @Test
    public void test03() {
        System.out.println("test03"); //Console'da sadece test03'u gorduk
    }
    @Test(dependsOnMethods = "test03")
    public void test04() {
        System.out.println("test04");
    }

    @Test(dependsOnMethods = "test04") //test04'e baglandi. Sadece test05'i calistirinca console'da No tests were found'u gorduk. Baglanti ustune baglanti yapinca
    //ve test seviyesinde calistirinca sorun oldu. Birden fazla baglanti varsa class seviyesinde calistirmak dogru olur.
    public void test05() {
        System.out.println("test05");
    }
}