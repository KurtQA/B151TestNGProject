package techproed.tests.day24_Priority_DependsOnMethods_SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class C03_DependsOnMethods {
    /*
        Test methodları birbirinden bağımsız çalışır. Methodları birbirine bağımlı çalıştırmak istersek
    DEPENDSONMETHODS parametresini @Test notasyonundan sonra bağlamak istediğimiz test methodunun adını
    yazarak belirtiriz. Kendisine dependsOnMethods ile bir test baglanan test, basarili bir sekilde pass olursa
    bagimli olan test de calisir, fail halinde bagimli olan test ignore edilir, hic calistirilmaz.
    Testler dependsonmethods ile bagimli hale getirilmezse birinde fail olmasi digerlerini etkilemez. Digerlerinin
    calistirildigini goruruz.
     */
    WebDriver driver;
    @Test  //priority=-1 ya da 0 da yazilabilir. Bu testimizde priority vermedik. Digerleri 1 ve 2 oldu. Bu testin priority'si defult 0 olarak sayildi
    public void test01() { //sadece test01'i calistirirsak driver'i acacak ve bekleyecek. Bu test icinde bir yonlendirme ayrica yok
        //Bu methodda driver ayarlarını yapalım
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test(priority = 1, dependsOnMethods = "test01") //Baslangicta oncelik sirasi vermedik ve bir teste bagimli yapmadik. Test02'yi bu test seviyesinde calistirdik.
    // NullPointerException aldik. Cunku bu testimizde chrome ayarlari yoktu. Driver'i bulamadi. test01'e depend yapinca bu testimizde calisti.
    public void test02() {
        //Amazon sayfasına gidelim
        driver.get("https://amazon.com");
    }

    @Test(priority = 2) //oncelik vermeyince class seviyesinde calistirinca da calismadi
    public void test03() {  //Priority ile Class level'da calistirinca calisti. Driver'i boylece gorebildi. Tek basina calistirinca dependsOn methodu olmadan calismadi
        //Amazonda arama kutusunda iphone aratalım
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone", Keys.ENTER);
    }
    //dependsOnMethods oldugunda birinci testten ucuncu teste atlayarak calistirma olabilir. Bunu priority vererek halledebiliriz

}