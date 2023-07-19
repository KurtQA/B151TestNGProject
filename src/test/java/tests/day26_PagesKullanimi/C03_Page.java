package tests.day26_PagesKullanimi;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;
import techproed.pages.TechproTestCenterPage;

public class C03_Page {
    @Test
    public void test01() {
        //https://testcenter.techproeducation.com/index.php?page=form-authentication
        Driver.getDriver().get("https://testcenter.techproeducation.com/index.php?page=form-authentication");

        //Page object Model kullanarak sayfaya giriş yapildigini test edin
        TechproTestCenterPage centerPage = new TechproTestCenterPage();// TechproTestCenterPage isimli Page modelimizi olusturmustuk. Ondan bir obje olusturduk
        centerPage.username.sendKeys(ConfigReader.getProperty("techpro_test_username"));
        centerPage.password.sendKeys(ConfigReader.getProperty("techpro_test_password"));
        ReusableMethods.bekle(3);
        centerPage.loginButton.click();
        ReusableMethods.bekle(2);
        Assert.assertTrue(centerPage.girisVerify.isDisplayed());

        //Sayfadan cikis yap ve cikis yapildigini test et
        centerPage.logoutButton.click(); //logout butonunun locate'ini TechproTestCenterPage Class'inda almistik ve oradaki logoutButton weblement'ini objemiz ismi ile
        //burada kullanabildik. click(); ile logout yani sayfadan cikisi gerceklestirdik.
        ReusableMethods.bekle(2);
        Assert.assertTrue(centerPage.logoutVerify.isDisplayed());

        //sayfayı kapatınız
        Driver.closeDriver();

    }
}