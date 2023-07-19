package tests.day25_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;


public class C03_PropertiesKullanimi {
    @Test
    public void amazonTest() {
        //amazon sayfasına gidelim
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl")); //.properties uzantili dosyada amazonUrl hazir olarak var.

        //basligin Amazon içerdiğini test edelim
        String actualTitle = Driver.getDriver().getTitle(); //title'i ConfigReader'dan boyle aliriz. dogrudan sadece getTitle()'i da kullanabiliriz
        String expectedTitle = ConfigReader.getProperty("expectedTitle"); //ConfigReader classindaki getProperty() methodundan expectedTitle'i parametre olarak girdik
        //configuration.properties dosyasinda expectedTitle=Amazon olarak bir datamiz daha var. Onu kullandik.
        //FileInputStream fis = new FileInputStream("configuration.properties"); ConfigReader class'inda configuration.properties dosyasi okundugundan expectedTitle datasini
        //alip kullanabilecegiz.
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        //aramakutusunda iphone aratalım
        Driver.getDriver().findElement(By.id("twotabsearchtextbox")).sendKeys(ConfigReader.getProperty("searchText"), Keys.ENTER); //searchText=iphone datamiz
        //configuration.properties dosyamizda var. Kodumuz calisinca key'i searchText olan datamizin iphone value'su amazon search box'da aartildigini gorduk. Bize key
        //degeri dondurulmus oldu.

        //sayfayı kapatalım
        Driver.closeDriver();

        //google sayfasına gidelim
        Driver.getDriver().get(ConfigReader.getProperty("googleUrl"));

        //sayfayı kapatınız
        Driver.closeDriver();

        //facebook sayfasına gidiniz
        Driver.getDriver().get("https://facebook.com");

    }

    @Test
    public void test01() {
        String amazonUrl = ConfigReader.getProperty("amazonUrl");
        String amazonExpectedTitle = ConfigReader.getProperty("expectedTitle");
        String aramaKutusu = ConfigReader.getProperty("searchBox");
        System.out.println(amazonUrl);
        System.out.println(amazonExpectedTitle);
        System.out.println(aramaKutusu);

    }
}