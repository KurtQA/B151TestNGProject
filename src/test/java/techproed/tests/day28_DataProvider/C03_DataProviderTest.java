package techproed.tests.day28_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;


public class C03_DataProviderTest {
    @DataProvider
    public static Object[][] blueRental() {
        return new Object[][]{{"sam.walker@bluerentalcars.com","c!fas_art"},
                {"kate.brown@bluerentalcars.com","tad1$Fas"},
                {"raj.khan@bluerentalcars.com","v7Hg_va^"},
                {"pam.raymond@bluerentalcars.com","Nga^g6!"}
        }; //burada bir email bir password olmak uzere 4 satir ve iki sutunumuz var. Excel tablosu gibi dusunelim. email ve password sutunlarimiz var. 4 veri 4 satir
    }

    @Test(dataProvider = "blueRental") //Bu kismi yazdiktan sonra blueRental yazisi uzerine geldik ve create object method'i tiklayinca yukaridaki arrayimiz olustu.
    public void test01(String mail,String password) { //mail ve password ile login olacagindan methodumuz parametrelerini String mail, String password girdik.
        //BlueRentalCar sitesine gidelim
        Driver.getDriver().get(ConfigReader.getProperty("blueRentalUrl")); //confuguration.properties'den url

        //DataProvider'daki mail ve password bilgileri ile login olalım
        BlueRentalPage rentalPage = new BlueRentalPage(); //Oncelikle blueRentalPage'den oradaki verilere ulasabilmek icin obje olusturmaliyiz.
        rentalPage.login.click(); //sayfaya girinca login butonuna click yapariz
        ReusableMethods.bekle(2);
        rentalPage.email.sendKeys(mail, Keys.TAB,password,Keys.ENTER); //email weblement'i ve sendKeys/Keys.Tab ile hem email hem password'u girebiliriz.
        ReusableMethods.bekle(2);

        //Sayfayı kapatalım
        Driver.closeDriver(); //sayfayi kapatinca ikinci veri({"kate.brown@bluerentalcars.com","tad1$Fas"},) icin test calisiyor ve her testte kapanip tekrar calisiyor.
    }
}