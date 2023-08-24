package techproed.tests.day28_DataProvider;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import techproed.pages.GooglePage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;

/*
Once test methdounu @Test ile olustur ve methodun parametresi uzerine gelip yeni bir method create et. Test methodumuz ismi ile olusan array methodunun body'si
icine verileri gir. Ayrica @Test yanina parantez icerisinde dataProvider ve array method ismini parametre olarak gir.


 */
public class C02_DataProviderTest {
    @DataProvider
    public static Object[][] arabalar() {
        return new Object[][]{{"volvo"},{"audi"},{"mercedes"},{"ford"}};
    }

    /*
        Google sayfasına gidip
        DataProvider ile belirtilen araç isimlerini aratalım
         */
    @Test(dataProvider = "arabalar") //dataProvider'dan datalari alacagiz. dataProvider'a arabalar methodumuzu isim olarak veriyoruz. Yani arabalar methodunu kullan
    public void test01(String araclar) { //Once @Test ve bu methodumuzu olusturduk. Sonra arabalar isimli array methodumuz olustu
        //Google sayfasına gidelim
        Driver.getDriver().get(ConfigReader.getProperty("googleUrl"));

        //Dataprovider'daki verilerle arama yapalım
        GooglePage googlePage = new GooglePage();
        googlePage.beforeGoogle.click();
        googlePage.aramaKutusu.sendKeys(araclar); //araclar String parametremiz idi. Kod calisirken google sayfasinda tek tek volvo, audi, mercedes, ford
        //yazilarinin arama kutusunda yazilip arama yapidigini gorduk. Normalde sendKeys ile bir String veri girerdik. Bu defa birden fazla veri girilmis oldu array ile
        googlePage.aramaKutusu.submit(); //submit yerine sendKeys icinde keys.ENTER da yazabilirdik.
        ReusableMethods.bekle(3);

        //Her arama için sayfa resmi alalım
        ReusableMethods.tumSayfaResmi();

        //Sayfayı kapatalım
        Driver.closeDriver();

    }
}