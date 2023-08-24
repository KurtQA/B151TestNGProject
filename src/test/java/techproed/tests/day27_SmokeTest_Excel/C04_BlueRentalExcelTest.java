package techproed.tests.day27_SmokeTest_Excel;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.pages.BlueRentalPage;

import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;
import techproed.utilities.ExcelReader;
import techproed.utilities.ReusableMethods;

public class C04_BlueRentalExcelTest {
    @Test
    public void test01() {
        //Excel dosyamızdaki tüm email ve password'ler ile
        //BlueRentalCar sayfasına gidip login olalım
        String dosyaYolu = "src/test/java/techproed/resources/mysmoketestdata.xlsx";
        String sayfaIsmi = "customer_info";
        ExcelReader reader = new ExcelReader(dosyaYolu,sayfaIsmi);

        //Sayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("blueRentalUrl"));
        BlueRentalPage rentalPage = new BlueRentalPage();
        //Bir loop oluşturup excel dosyasındaki tüm veriler ile login olalim
        for (int i = 1; i <= reader.rowCount(); i++) {//-->i'yi 1 den başlattık çünkü veriler 1. satırdan başlıyor
            String email = reader.getCellData(i,0);
            String password = reader.getCellData(i,1);
            System.out.println(email +" || "+password);
            rentalPage.login.click();
            ReusableMethods.bekle(2);
            rentalPage.email.sendKeys(email, Keys.TAB, password,Keys.ENTER);
            ReusableMethods.bekle(2);
            rentalPage.loginVerify.click();
            Assert.assertTrue(rentalPage.logOut.isDisplayed());
            ReusableMethods.bekle(2);
            rentalPage.logOut.click();
            ReusableMethods.bekle(2);
            rentalPage.ok.click();
        }

        //sayfayı kapatını
        Driver.closeDriver();




    }
}
