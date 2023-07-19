package tests.day24_Priority_DependsOnMethods_SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_Priority {
    /*
    TestNG(Test New Generation). It works only with Java. TestNG gives more control to testers and makes testing effective
        Junitte test methodlarını istediğimiz şekilde sıralamak için method isimlerini alfabetik ve numerik sıralı
   olarak yazmamız gerekiyordu. TestNG frameworkunde bu sıralama için @Test notasyonundan sonra parametre olarak
   (priority = 1 ) gibi öncelik sırası belirterek test methodlarımızı sıralayabiliriz.
   Priority kullanmadığımız methodda priority değerini 0(sıfır) olarak kabul eder.
   Java'da yukaridan asagiya dogru bir calistirma soz konus idi. TestNG'de natural order'a gore calistirma var. Biz
   test01 sonra test07 daha sonra test05 yazarsak, test07 daha yukarida olsa da once test05'i console'da goruruz. Ancak
   bu siralamayi priority kullanarak degistirebiliriz. priority=-3 priority=1 olursa once priority=-3 calisir
     */
    WebDriver driver;  //Class level'da yazdik. Boylece diger methodlarda da kullanabiliriz
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //Chrome ayarlarini BeforeMethod icerisinde yazdik. Her methoddan once BeforeMethod calisacagi icin once chrome ayarlari calisacak.
    }

    @Test(priority = 1)
    public void amazonTest() {
        driver.get("https://amazon.com");//-->ikinci olarak amazon çalışsın
    }
    @Test
    public void youtubeTest() {
        driver.get("https://youtube.com");//-->youtube önce çalışsın--> Default 0 olduğu için önce bu method çalışır
        //priority yazmayan Test method’u varsa priority= 0 kabul edilir, siralama buna gore yapilir
    }
    @Test(priority = 2)
    public void facebookTest() {
        driver.get("https://facebook.com");//-->Son olarak facebook çalışsın
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
    //Console'da: sirasiyla youtube Test, amazon Test, facebookTest alt alta goruldu. Yani priority dikkate alinarak calistirma yapildi

}