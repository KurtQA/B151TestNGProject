package techproed.tests.day30_Listeners;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;

@Listeners(techproed.utilities.Listeners.class) //Listeners classimiz yolu
public class C03_ListenersTest3 {
    /*
        Eğer Testimizin başarısız(fail) olma durumunda tekrar çalışmasını istersek,
     @Test notasyonundan sonra retryAnalyzer parametresi ile listeners class'ının yolunu belirtiriz.
     Bununla birlikte ITestListeners arayüzündeki methodlarıda kullanabilmek için yine class'dan önce
     @Listeners notasyonu kullanırız.
     */
    @Test(retryAnalyzer = techproed.utilities.Listeners.class)
    //@Test(retryAnalyzer = techproed.utilities.Listeners.class,invocationCount, dataProvider, priority kullanmak
    // istedigimizde aralarina virgul koyarak testimizde kullanilmalarini saglayabiliriz)
    public void amazonTest() {
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        Driver.getDriver().findElement(By.id("lkhjlkjhlj")).click();
    }
}