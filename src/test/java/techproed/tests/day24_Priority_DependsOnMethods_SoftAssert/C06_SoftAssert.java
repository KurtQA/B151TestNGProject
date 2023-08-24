package techproed.tests.day24_Priority_DependsOnMethods_SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class C06_SoftAssert {
    /*
        SoftAssert kullanımında junitteki daha önce kullandığımız methodların aynısını kullanırız.
    Onceden kullandığımız hard assertion islemi nerede hata alırsa orda testlerin çalışmasını durdurur.
    SoftAssert JUnit'den gelmez, TestNG'den gelir.
    SoftAssert'te birden fazla assertion kullandigimizda assertAll() methodunu nerede kullanırsak orada assertionlar sonlanır
    ve hata varsa bunu bize konsolda belirtir. assertAll() method marks which soft asserts passed, and which ones failed. assertAll() is the last point.
     */
    WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void test01() {
        //Amazon sayfasına gidelim
        driver.get("https://amazon.com");

        //Url'in https://www.amazon.com.tr olup olmadığını doğrulayınız
        SoftAssert softAssert = new SoftAssert();//-->SoftAssert kullanabilmek için SoftAssert class'ından obje oluşturmalıyız
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com.tr");//-->bilerek hata alacagiz. Normalde URl'de 'tr' yok
        //test fail olsa dahi devam edecek.

        //Başlığın best içerdiğini test edelim
        softAssert.assertTrue(driver.getTitle().contains("best"));//-->Bilerek hata alacagiz. Baslikta
        // best olmadigini biliyoruz. Console'da expected true but false found yazildi
        //assertion yaparken softassert objesini kullaniyoruz ve devaminda assertTrue() methodu ile devam ediyoruz.

        //Arama kutusunda iphone aratalım
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone", Keys.ENTER);

        //Sonucun samsung içerip içermediğini test edin (Sonucun iphone icerdigini samsung icermedigini gorduk
        WebElement sonucYazisi = driver.findElement(By.xpath("(//*[@class='sg-col-inner'])[1]")); //class ve value'yu ixpath'e gore
        //alinca bu class ve value'den 90 tane oldugu goruldu. [1] index yazinca 1 of 1 oldu ve ekranda sonuc yazisi secili oldu
        softAssert.assertTrue(sonucYazisi.getText().contains("samsung"));//-->Bilerek hata alacagiz
        //Console'da expected true but false found yazildi
        //artik testimiz bitti. assertAll() yazarak buraya kadar testi sonlandir demis oluruz
        softAssert.assertAll(); //Bunu yazdiktan sonra eger bir islem yaparsak o islemi dikkate almaz
        System.out.println("Burası çalışmaz"); //Bu kismi ekranda gormeyecegiz
    }

    @Test
    public void test02() {
        //Amazon sayfasına gidelim
        driver.get("https://amazon.com");

        //Url'in https://www.amazon.com.tr olup olmadığını doğrulayınız
        SoftAssert softAssert = new SoftAssert();//-->SoftAssert kullanabilmek için SoftAssert class'ından obje oluşturmalıyız
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com.tr");//-->bilerek hata alacagiz

        //Başlığın best içerdiğini test edelim
        softAssert.assertTrue(driver.getTitle().contains("best"));//-->Bilerek hata alacagiz

        //Arama kutusunda iphone aratalım
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone", Keys.ENTER);

        //Sonucun samsung içerip içermediğini test edin
        WebElement sonucYazisi = driver.findElement(By.xpath("(//*[@class='sg-col-inner'])[1]"));
        softAssert.assertTrue(sonucYazisi.getText().contains("samsung"));//-->Bilerek hata alacagiz
        System.out.println("Burası çalıştı"); //Bu kismi console'da gorduk. assertAll() oncesinde oldugu icin gorebildik. Sonrasinda olsaydi gormezdik
        softAssert.assertAll(); //assertAll()'u burada degil de test02'nin Arama kutusunda iphone aratalim kisminda
        //yazmis olsaydik oraya kadar testimiz calisirdi, sonucun samsung icerip icermedigini test edin kismini
        //yani bu bolumu calistirmazdi
    }

    @Test
    public void test03() {
        //Amazon sayfasına gidelim
        driver.get("https://amazon.com");

        //Url'in https://www.amazon.com.tr olup olmadığını doğrulayınız
        SoftAssert softAssert = new SoftAssert();//-->SoftAssert kullanabilmek için SoftAssert class'ından obje oluşturmalıyız
        softAssert.assertNotEquals(driver.getCurrentUrl(),"https://www.amazon.com.tr");

        //Başlığın best içerdiğini test edelim
        softAssert.assertFalse(driver.getTitle().contains("best"));

        //Arama kutusunda iphone aratalım
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone", Keys.ENTER);

        //Sonucun samsung içerip içermediğini test edin
        WebElement sonucYazisi = driver.findElement(By.xpath("(//*[@class='sg-col-inner'])[1]"));
        softAssert.assertFalse(sonucYazisi.getText().contains("samsung"));
        softAssert.assertAll();
        System.out.println("Burası çalıştı");  //Normalde assertAll() kismindan sonrasi calismaz, yazdirilmazdi
        //ancak "Burasi calisti" kismi assertAll() sonrasi olsa dahi console'da gorulur. Fail olacak bir durum soz konusu
        //degilse. Bu testimizde sirsiyla tr, best ve samsung icermedigi halde yukaridaki testlerde oldugu gibi
        //assertTrue degil assertFalse'u kullandik. Yani iceriyor mu diye sorarken icermemesini beklemis olduk.
        //icermedigi icin de testimiz pass oldu ve assertAll() sonrasi da console'da goruldu. Yukaridaki testlerimizde
        //testimiz fail verecek sekilde asserTrue'yu kullandik bu nedenle assertAll() sonrasi console'da yazilmadi


    }
}