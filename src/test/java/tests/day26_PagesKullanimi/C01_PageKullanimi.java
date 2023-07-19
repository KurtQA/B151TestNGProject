package tests.day26_PagesKullanimi;

import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.OpenSourcePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C01_PageKullanimi {
    @Test
    public void test01() {
        //https://opensource-demo.orangehrmlive.com/web/index.php/auth/login adrese gidelim
        //Driver.getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Driver.getDriver().get(ConfigReader.getProperty("opensourceUrl"));


        //kullaniciAdi, kullaniciSifre, submitButton elementlerini locate edelim. Locate'lerini OpenSourcePage class'inda aldik ve orada tuttuk.
        //kullanici=Admin -->.properties dosyasında. value websayfasinda userName value'su
        //kullaniciSifre=admin123 -->.properties dosyasında. value Websayfasinda password value'su
        /*
        Locateleri oluşturduğumuz page class'ındaki webelementlere ulaşabilmek için
        aşağıdaki örnekteki gibi classdaki constructor'dan obje oluşturduk.
         */
        OpenSourcePage sourcePage = new OpenSourcePage();
        //https://opensource-demo.orangehrmlive.com/web/index.php/auth/login adresine gidince acilan sayfada Login altinda Username:Admin Password:admin123 oldugunu gorduk ve
        // configuration.properties dosyamizda bu URL, Username ve password'a buna gore value yazdik. Key olarak userName yerine kullaniciAdi, password yerine sifre yazdik.
        //Ayrica Login altindaki Username ve password checkbox;larin locatelerini inspect yaparak aldik

        sourcePage.username.sendKeys(ConfigReader.getProperty("kullaniciAdi"));  //sourcePage objesi ile OpenSourcePage clasindaki weblement'imiz username'i alabildik ve onunla
        //da sendKeys gibi methodlari kullanabiliriz.
        sourcePage.password.sendKeys(ConfigReader.getProperty("sifre"));
        //username ve password kutucuklarini bu sekilde doldurduktan sonra locate;ini aldigimiz login butonuna click yapacagiz
        ReusableMethods.bekle(5);//-->Thread.sleep methodunu kullandık. Bu class'da JUnit'de oldugu gibi extends TestBase yapmadigimiz icin bekle methodunu ayrica iceren
        //ReusableMethods class ismi ile bi sekilde kullanabiliriz.
        sourcePage.loginButton.click();

        //Login Testinin basarili oldugunu test et
        //Yukaridaki kodumuz calistirinca basarili bir sekilde sonraki sayfaya Dasboard'un oldugu sayfaya gecebildik. Basarili bir gecis oldugunu sadece yukaridaki DashBoard
        //yazisinin locate'ini alip isDisplayed ile assert yaparsak basarili bir gecis oldugunu dogrulamis oluruz. Ya da yeni gectigimiz sayfanin URL'inin locate'i alinabilir basarili
        //bir gecis oldugunu gosterebilmek icin.
        Assert.assertTrue(sourcePage.verify.isDisplayed()); //verify OpenSourcePage class'inda Dashboard locate'ini aldiktan sonra weblement olarak ona verify ismini vermistik

    }
}