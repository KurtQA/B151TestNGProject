package tests.day23_Annotation;

import org.testng.annotations.*;

public class C01_Annotation {
    /*
    BeforeSuite , test ve class en basta bir sefer. AfterSuite, test ve class en son da bir sefer calisir
    before ve after methodlar ise her testin basinda sonunda bir kez calisir. Biz en sik @BeforeMethod ve @AfterMethod'u kullaniriz
     */
    @BeforeSuite
    public void suite() {
        System.out.println("Her şeyden önce bir kez BeforeSuite çalışır"); //chrome driver'dan once de calisir
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("Testlerden önce bir kez çalışır");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("Her class'dan önce bir kez çalışır");
    }
    @BeforeMethod
    public void setUp() {
        System.out.println("Junitteki @Before notasyonu gibi her methoddan önce çalışır");
    }

    @Test
    public void test01() {
        System.out.println("Test1 çalıştı");
    }

    @Test
    public void test02() {
        System.out.println("Test2 çalıştı");
    }

    @Test
    public void test3() {
        System.out.println("Test3 çalıştı");
    }
    @AfterSuite
    public void afterSuite() {
        System.out.println("Her şeyden sonra 1 kez çalışır");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("Testlerden sonra 1 kez çalışır");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("Her class'dan sonra 1 kez çalışır");
    }
    @AfterMethod
    public void tearDown() {
        System.out.println("Junit'teki @After notasyonu gibi her method'dan sonra çalışır");
    }
    /*
    Console'da:
    Her şeyden önce bir kez BeforeSuite çalışır //@BeforeSuite
    Testlerden önce bir kez çalışır   // @BeforeTest 10 testimiz varsa 1 kez testler oncesi calisir
    Her class'dan önce bir kez çalışır  //@BeforeClass

    Junitteki @Before notasyonu gibi her methoddan önce çalışır //@BeforeMethod
    Test1 çalıştı
    Junit'teki @After notasyonu gibi her method'dan sonra çalışır //@AfterMethod

    Junitteki @Before notasyonu gibi her methoddan önce çalışır
    Test2 çalıştı
    Junit'teki @After notasyonu gibi her method'dan sonra çalışır

    Junitteki @Before notasyonu gibi her methoddan önce çalışır
    Test3 çalıştı
    Junit'teki @After notasyonu gibi her method'dan sonra çalışır

    Her class'dan sonra 1 kez çalışır  //@AfterClass
    Testlerden sonra 1 kez çalışır     //@AfterTest
    Her şeyden sonra 1 kez çalışır     //@AfterSuite

===============================================
    Default Suite
    Total tests run: 3, Passes: 3, Failures: 0, Skips: 0
            ===============================================
 3 testimiz varsa herbirisi icin @AfterTest ayri ayri calismayacak bir kez calisacak

     */

}