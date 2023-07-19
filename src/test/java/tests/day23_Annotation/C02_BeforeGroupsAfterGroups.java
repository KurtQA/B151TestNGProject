package tests.day23_Annotation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_BeforeGroupsAfterGroups {
    //Bazi testlerimizi tek tek calistirmak isteyebilecegimiz gibi gruplandirarak tek seferde calistirmak isteyebiliriz

    WebDriver driver;  //class seviyesinde bu sekilde yazarak diger methodlarda kullanabilmemizin yolunu actik.
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("En başta beforeSuite çalışır");//test fail, null verse de beforesuite calisir. Testi run ederken tam sonucu gormeden
        //test calismaya devam ederken dahi beforesuite calisti ve biz console'de En basta beforeSuite calisir'i gorduk
    }
    @BeforeGroups({"erol","evren"})  //test3()'u de gruba dahil etmek istedik. Basta ("erol") olarak @BeforeGroups yanina parametreyi girmistik.
    //test3() icin farkli bir parametre daha girip gruba dahil etmek isteyince ({"erol", "evren"}) evreni bu sekilde ekleyince gruplandirma
    //islemi test3() icinde basarili bir sekilde gerceklesti.
    public void beforeGroups() {
        //Chrome ayarlarimizi yazdik
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //groups parametresi ile baglamadan once testimiz calismadi ve nullpointer exception verdi
    //daha sonra baglamayi yapinca alt alta iki testimiz calisti ve hem amazon'a hem youtube'a bir kere run etme ile gitti
    //Test seviyesinde calistirdik.
    //groups="" tirnak isaretleri icerisine istedigimiz yazabiliriz. Isimleri biz verebiliriz
    @Test(groups = "erol") //groups parametresi ile testi @BeforeGroups'a baglariz. Bunu @Test annotation'i yanina yazariz
    public void test01() {
        String amazonUrl = "https://amazon.com";
        driver.get(amazonUrl);
    }

    @Test(groups = "erol") //groups parametresi ile testi @BeforeGroups'a baglariz.
    public void test02() {
        String youtubeUrl = "https://youtube.com";
        driver.get(youtubeUrl);
        driver.close(); //Test seviyesinde calistirsaydik test3() methodumuz calismazdi, ancak class seviyesinde calistirsaydik
        //test3() methodu da calisirdi. Cunku test seviyesinde calistirma halinda close yapilan ve bulunamayan driver'i class seviyesinde
        //bulabilirdi.
    }

    @Test(groups = "evren")
    public void test03() {
        String facebookUrl = "http://facebook.com";
        driver.get(facebookUrl);
    }

    /*
    Console'da:
    ===============================================
Default Suite
Total tests run: 3, Passes: 3, Failures: 0, Skips: 0
===============================================


     */
}
