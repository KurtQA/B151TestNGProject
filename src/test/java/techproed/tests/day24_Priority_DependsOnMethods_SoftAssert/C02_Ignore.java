package techproed.tests.day24_Priority_DependsOnMethods_SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_Ignore {
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    /*
    Birden fazla test methodu çalıştırmak istediğimizde o anlık gereksiz gördügümüz test methodunu
    atlamak (ignore) isteyebiliriz. Bunun için @Test notasyonu üstüne yada yanına @Ignore notasyonu
    eklememiz yeterlidir.@Ignore notasyonu ile atladığımız method konsolda gözükmez raporlamalarda gözükür
          Eğer bir methodu tamamen iptal etmek isterseniz @Test notasyonundan sonra parametre olarak (enabled=false)
    kullanabilirsiniz.
     */

    @Test @Ignore //Bu test atlanmis olacak, console'da gorulmeyecek ancak raporlama yapildiginda gorulecek
    public void amazonTest() {
        driver.get("https://amazon.com");
    }
    @Test(enabled = false) //Bu test tamamiyle iptal edilmis oldu. Console'da ve raporlamada gorulemeyecek. Testi calistirmak istersek ayrica enabled=true yazmaya gerek yoktur
    public void youtubeTest() {
        driver.get("https://youtube.com");
    }
    @Test
    public void facebookTest() { //Bu test calisacak, console'da ve raporlamada gorulecek
        driver.get("https://facebook.com");
    }
    //Sadece facebookTest calisti. Console'da onu gorduk

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}