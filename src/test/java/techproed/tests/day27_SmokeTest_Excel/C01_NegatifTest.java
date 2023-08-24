package techproed.tests.day27_SmokeTest_Excel;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ReusableMethods;


public class C01_NegatifTest {
    //Description:
    //Kullanimda olmayan kullanıcı adi ve şifre ile giriş yapilamamali
    //Acceptance Criteria
    //Customer email: fake@bluerentalcars.com
    //Customer password: fakepass
    //Error:
    //User with email fake@bluerentalcars.com not found

    @Test(groups = "smoke")
    public void test01() {
        //https://www.bluerentalcars.com/ adresine gidelim negatif senaryo ile login olamadığımızı test edelim
        Driver.getDriver().get(ConfigReader.getProperty("blueRentalUrl")); //Configuration.properties file'da blueRentalUrl olarak url'i yazmistik. Bunu oradan aldik.
        BlueRentalPage blueRentalPage = new BlueRentalPage(); //BlueRentalPage class ismi ile obje olusturduk. Bu objemiz ile BlueRentalPage class'indaki variable'ari yani
        //weblementleri alabilecegiz.
        blueRentalPage.login.click(); //BlueRentalPage class'indaki login isimli weblement'i kullandik. click ile login yaptik.
        blueRentalPage.email.sendKeys(ConfigReader.getProperty("fakeEmail"), Keys.TAB,
                ConfigReader.getProperty("fakePassword"),Keys.ENTER); //Configuration.properties file'daki BlueRental com ile ilgili fakeEmail ve fakePassword'u kullanarak
        //email ve sifreye bunlari girmis olduk. BlueRentalPage'deki email weblement'i ile ve keysTAB ile bir sonraki kutucuga yani password'a gidebildik.
        //BlueRentalPage class'inda gercek email ve password'un nasil olacagi ornegin bir buyuk harf bir kucuk harf bir sayi ve bir noktalama isareti olsun denir. Buna gore BlueRentalPage
        //Clsaa'inda password'u Ba123. olarak girmmistik ve bu dogru bir password olur. Ancak yanlis bir password ile giris yapilamadigini da test ederken fake emaili istenenden farkli
        //yazariz. Ornegin asd123. Burada bir tane dahi buyuk harf yok ve noktalama isareti yok. Hatta birden fazla farkli isimde fake password olustururuz page'de:
        //fakePassword=asd123, fakePassword2=Asd123, fakePassword3=as123. gibi. Bunlar dogru passwordun hicbir sartini tasimiyor. Bunlarla giris yapamadigimizi dogrulamamiz gerekir.


        //Buraya kadar olan kisimda kodu calistirinca websayfasinda User with email fake@blurentalcars.com not found hata mesajini gorduk. Bu hata mesajinin uzerine gelince yazi duruyor
        // ya da Elements'de iken HTML kodlarinda Source kismina gelip durdurma islemini yapabiliriz. Biz giris yapilamadigini bu yaziyi gorerek ya da
        //giris yapmaya calistigimiz halde tekrar Login butonunu gorerek anlariz. Giris yapamadigimizi ya bu hata mesajinin locate'ini alarak ya da hala gordugumuz Login butonu locate'ini
        // alarak dogrulamasini yapabiliriz.

        //Biz hata mesajinin locate'ini aldik ve locate'i BlueRentalPage class'ina yazdik
        ReusableMethods.visibleWait(blueRentalPage.mesajVerify,5); //visible wait nedir? Explicitly waitdir. Weblement'i gorunur olana kadar bekle. Hata mesaji weblement'i gorunur
        //oluncaya kadar bekle. Bunu yapmazsak o mesaj hizlica kaybolabilir. Gorunur oluncaya kadar bekle deriz. Hizlica kaybolmasi ya da gorunmeden assertion yapilmasi halinde hata
        //alabilirdik.
        Assert.assertTrue(blueRentalPage.mesajVerify.isDisplayed()); //mesajVerify hata mesajinin locate'i ile ilgili idi. Bu weblement gorunuyorsa giris yapamamisiz demektir. Bu
        //nedenle assertTrue kullandik.
      Driver.closeDriver();
    }

}