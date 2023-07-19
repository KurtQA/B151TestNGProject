package tests.day26_PagesKullanimi;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;


import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C04_PozitifTest {
    @Test
    public void test01() {

        //Acceptance Criteria:
        //Admin olarak, uygulamaya giriş yapabilmeliyim
        //https://www.bluerentalcars.com/
        Driver.getDriver().get(ConfigReader.getProperty("blueRentalUrl"));

        //Admin email: jack@gmail.com
        //Admin password: 12345
        BlueRentalPage blueRentalPage = new BlueRentalPage();
        blueRentalPage.login.click(); //sayfaya gidince karsimiza login butonu gelmisti. Onun locate'ini aldik ve click ile email ve password'un oldugu sayfa acildi
        ReusableMethods.bekle(1);
        blueRentalPage.email.sendKeys(ConfigReader.getProperty("blueRentalEmail"), Keys.TAB ,
                ConfigReader.getProperty("blueRentalPassword"),Keys.ENTER); //Bu sekilde hem email hem password kutucuklarina girip Configuration.properties file'daki ilgili
        //bilgileri girebildik

        //Giriş yapıldığını doğrulayınız
        Assert.assertTrue(blueRentalPage.verify.getText().contains("Jack")); //Yukaridaki kod calisinca acilan sayfada Jack Nicholson yazisini gorduk. Onun loctae'ini BlueRentalPage
        //class'inda @FindBy ile yazdik ve ona weblement olarak verify ismini verdik. Jack Nicholson weblementinin Jack icerdigini dogruladik. Boylece bu yazinin oldugu sayfaya giris
        //yapilmasi giris yapildigi anlamini tasiyacak

    }
}