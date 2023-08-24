package techproed.tests.day25_POM;

import org.testng.annotations.Test;
import techproed.utilities.Driver;
//Tum classlarda ayni tek bir objenin kullanilmasi icin olusturulan class'a Singleton design pattern denir.
/*
Page Object Model
Framework design
Page Object Model temelde 3 package icerir
➢ Tests : Sadece testleri execute etmek icin gerekli
adimlari yazacagimiz class’lar icerir. Hicbir data
girisi yapmayacagiz
➢ Pages : Test yapacagimiz sayfalardaki Web
Elementlerini locate etmek ve temel
methodlari olusturmak icin kullanilir.
➢ Utilities : Driver,TestBase ve ConfigurationReader
class’larini icerir

 */

public class C01_DriverTest {
    @Test
    public void test01() {
        Driver.getDriver().get("https://amazon.com");
        Driver.getDriver().get("https://youtube.com");
        Driver.getDriver().get("https://facebook.com");
        Driver.quitDriver();  //tum sayfalar icin acilan driver'i kapat
        /*
        Bu methodda driver'ı if bloğu içine almadığımızdan dolayı her sayfa için yeni bir driver açtığının örneğini gösterdik
        getDriver() methodunu Driver Class'i icerisinde static olarak olusturmustuk. Bu methodu static oldugu icin class'inin ismi ile cagirdik. Bu method
        icersinde if statement vardi.Driver'i her çağırdığımızda yeni bir pencere açılmasının önüne geçmek için if bloğu içinde Eğer driver'a değer
        atanmamışsa yani driver bos ise ona değer ata(bu degeri ata:
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); ,eğer değer atanmışsa
            Driver'i aynı sayfada return et.
            Asagida amazon, youtube ve facabook'a getDriver mathodu ve get methodu ile gidecegiz. Driver amazon'a gittiginde bir deger atanmadi ise klasik olarak chrome
            ayarlari deger olarak atanacak. Daha sonra driver youtube web sitesine giderken artik driver'in amazon sayfasina giderken bir deger almasi yani bos olmamasi
            nedenli ayni deger ile ayni driver ile yeni bir deger almadan yeniden bir driver acmadan youtube'a gidilecek. Driver youtube'a giderken zaten acik ve bir degeri var.
            Bundan sonra baska bir sayfaya da ayni driver ile gidilebilir. Ayni degerdeki driver return edilecek.

            Assertion yapacak olursak title ile ilgili olarak:
            Stirng actualTitle=Driver.getDriver().getTitle();
            Assert.assertTrue(actulaTitle.toLowerCase().contains("amazon")); title kucuk harflerle yazildigini bildigimizden toLowerCase() methodunu da kullandik.
             */


    }

    @Test
    public void test02() {
        Driver.getDriver().get("https://amazon.com");
        Driver.getDriver().get("https://youtube.com");
        Driver.closeDriver(); //Farkli sayfalara gittigimizde birden fazla driver acilmasin, bir driver uzerinde diger sayfalara da gidebilsin
        Driver.getDriver().get("https://facebook.com"); //bir ustte driver'i close ile kapattik ve bu satir icin kod calisinca driver'i acik bulamadigindan hata verdi
        //bunun uzerine Driver class'i icerisinde closeDriver methoduna sunu ekledik:
        // if(driver !==null){
        //driver.close();
        //driver=null; ekledik. Yani driver'a deger atanmis ise driver'i kapat ve bosalt demis olduk. Bosaltma olunca driver'a yeniden deger atanabilecek ve isleme daha
        //sonra devam edebilecegiz. Bu sayede facebook sayfasina giderken driver deger alabildi ve artik hata almadik.
        //Bundan sonra ayrintili olarak bir close  methodu yazmayip Driver Class'indan closeDriver() methodunu Driver.closeDriver() olarak kullanacagiz
    }

    @Test
    public void test03() {
        //Driver driver = new Driver();
        //driver.getDriver().get("https://amazon.com");
        //Bu obje Driver Class'inin default constructor'i ile olusturuldu. Biz Driver Class'i consturctor'ini private yapar yapmaz objemizin new sonrasi Driver kismi kizardi
        /*
        Bu örnekte Driver class'ında singeleton pattern kullanarak obje oluşturmanın önüne
        geçmiş olduk
         */
    }
}