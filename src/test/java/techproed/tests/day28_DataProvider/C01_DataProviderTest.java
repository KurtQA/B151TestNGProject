package techproed.tests.day28_DataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class C01_DataProviderTest {

    /*
        Dataprovider, bir çok veriyi test caselerde loop kullanmadan aktarmak için kullanılır.
    TestNg'den gelen bir özelliktir. 2 boyutlu Object Array return eder.
    Kullanımı için @Test notasyonundan sonra parametre olarak (dataProvider="method ismi") yazılır.
    Kaç tane veri ile çalışacaksak test methoduna o kadar parametre eklenir. Ornegin BlueRental websitesine gittikten sonra email ve password
    gireceksek method parametresi olarak iki String data yazacagiz email ve password seklinde
    Data driven testing(DDT) de datalarımızı 3 farklı şekilde kullanabiliriz.
        1-.properties dosyasından datalari alabiliriz-->ConfigReade.getProperty() ile
        2-Excel dosyasından dataları alabiliriz-->ExcelReader class'ı ile
        3-DataProvider kullanarak dataları alabiliriz-->DataProvider methodu ile
     */

    //Baslangicta asagidaki iki satirlik methodu yazdik ve isimler'in uzeri koyu renkte idi. Uzerine gelince isimler ismi ile bir method create etmemiz soylendi. Biz de
    //tiklayinca @DataProvider notasyonlu isimler Object'imiz olustu. ve biz return kismini ekledik
   // @Test(dataProvider = "isimler")
    //public void test01(String data) { }

    @DataProvider(name = "test02")//-->Bu dataProvider'ı test02 methodu için kullan anlamına gelir
    public static Object[][] isimler() {
        return new Object[][]{
                {"esen"},
                {"mehmet"},
                {"esma"},
                {"ali"},
                {"mert"},
                {"burcu"},
                {"yunus"}};
    }
    @DataProvider
    public static Object[][] arabalar() {
        return new Object[][]{
                {"ford"},
                {"mercedes"},
                {"volvo"},
                {"audi"}};
    }
    /*
    Oluşturduğumuz dataprovider methodunu başka bir test methodunda da kullanabiliriz.
    Bunun için @DataProvider notasyonundan sonra yeni oluşturduğumuz methodun ismini (name="test02") olarak
    belitmemiz gerekir
     */

    @Test(dataProvider = "isimler")
    public void test01(String data) { //isimler array'imizde aratilacak bir data olacak her seferinde
        System.out.println(data);
    }
    //isimler array kodumuzu calistirinca console'da herbir data icin ayri ayri test01[mehmet] gibi array olustugunu ve her array yaninda sirasiyla index
    //numaralarinin verildigini gorduk.

    @Test(dataProvider = "test02")
    public void test02(String isim) {
        System.out.println(isim); //Burada da biz yukaridaki dataProvider'daki ayni verileri kullandik. Console'da bu sefer array olarak degil isimler String olarak alt alta
        //goruldu.
    }

    @Test(dataProvider = "arabalar") //arabalar yani name ile dataProvider'a farkli bir isim atadik.
    public void test03(String arabalar) {
        System.out.println(arabalar); //Bu kisma geldikten sonra diyelim ki test01'i calistiralim. Bu hata verir. Cunku ayni dataProvider'i test02 icin kullandik. Oncesinde
        //@DataProvider(name = "test02") yazarak dataProvider'a test02'yi yeni name olarak girdik. Baslangicta dataProvider'a isimler'i name olarak yazmistik. Bu da Test01'i
        //kullan anlamina geliyordu. Istersek test02 icin yeni bir dataProvider olusturabilirdik ancak biz bir tane dataProvider olusturduktan sonra ayni verileri kullanacagimiz
        //icin onceki dataProvider'i kullanmak istedik.
        //Testleri ayri ayri kendi seviyelerinde calistirdik. Bu testi calsitirnca run:4, Passess:4, Failures:0 oldu ve arraydeki veriler ayri ayri arraylar olarak 4
        //tane yazildi


    }
}