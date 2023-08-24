package techproed.tests.day28_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;


public class C04_DataProviderConfigReaderTest {
    //Oncelikle Configuration.properties'e mail ve password verilerini ekledik.
    @DataProvider
    public static Object[][] blueRental() {
        //Object methodumuzun body'si icerisinde Configuration.properties dosyasindaki verileri okyabilmek icin ConfigReader'i kullandik
        // ve getProperty icine  ilk parametre olan mail'i yine ConfigReader ve getProperty kullanip ikinci parametreyi sifre icin girdik. Cunku her seferinde
        // iki veri okunacak ve bu key'lerin value'leri alinabilecek. 4 veri icin 4 satir ve 2 sutun olacakti excel'deki gibi. Bu nedenle 4 kez ConfigReader.getProperty
        //her bir satir icin yazildi.
        return new Object[][]{{ConfigReader.getProperty("mail1"),ConfigReader.getProperty("sifre1")},
                {ConfigReader.getProperty("mail2"),ConfigReader.getProperty("sifre2")},
                {ConfigReader.getProperty("mail3"),ConfigReader.getProperty("sifre3")},
                {ConfigReader.getProperty("mail4"),ConfigReader.getProperty("sifre4")}};
    }
    //Yukaridaki object methodumuz verilerin saklandigi bir method oldugundan bundan sonra bu sekilde olusturdugumuz object methodlari DataProviderUtil class'ina
    //koyalim. Boylece test class'larinda sadece test methodlarimiz olsun.

    /*
   Dataprovider kullanarak .properties dosyasındaki veriler ile BlueRentalCar sitesine login olalim
    */
    @Test(dataProvider = "blueRental")//blueRental uzerinden object methodumuzu olusturduk
    public void test01(String mail, String password) {
        //Bu asamaya gelmeden once yukaridaki object methodumuz icini ConfigReader'lar ile doldurduk. Yani 1. adimda test nmethodumuzu olusturduk ve @Test
        //yanina dataProvider ve method adini yazdik. test01 methodumuz parametrelerini 'de girmistik. Sonra object methodumuzu olusturduk ve tamamladik. Daha sonra
        //asagida devam eden kodu yazdik. Buradan sonrasi onceki class'larda yaptimiz ayni turden islemlerle devam edecek
        Driver.getDriver().get(ConfigReader.getProperty("blueRentalUrl"));
        BlueRentalPage rentalPage = new BlueRentalPage();
        rentalPage.login.click();
        ReusableMethods.bekle(2);
        rentalPage.email.sendKeys(mail, Keys.TAB,password,Keys.ENTER);
        ReusableMethods.bekle(1);
        //Testimiz gecti ve console'un sol tarafinda sirasiyla test01[] her parantez icinde ilk satir veri ve sonraki veriler birer array gibi yazildi
        Driver.closeDriver();
  //Test classlarimizda sadece test methodumuz olur ve verileri farkli class'lardan cekeriz.
    }
}