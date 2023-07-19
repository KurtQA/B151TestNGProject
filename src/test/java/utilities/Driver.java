package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
/*
POM(Page Object Model)
    Test senaryolarının daha az kod ile yazılmasına ve bakımının daha kolay yapılmasına
olanak sağlayan yazılım test yöntemidir. TestNG de ve CUCUMBER da POM kalıbını kullanırız.

We have 1 instance, 2 methods in this singleton driver class
private static WebDriver driver;
1. getDriver: setup and instantiate the driver object It will return driver object. Driver bos ise deger ata ve driver return et. Bos olani kapatmak olmaz
2.closeDriver: quits the driver. Driver bir degere sahip, boylece acik ise quit ile kapat ve driver'i bosalt. Boylece driver'a yeniden deger atanabilecek
 */

public class Driver {
    private Driver(){ //Bu Driver Class'inin defoult constructor'i olup bunu private yaparak obje olusumunu onune gectik
        /*
        Driver class'ından obje oluşturmanın önüne geçmek için
      default constructor'ı private yaparak bunun önüne geçebiliriz.
      Bu uygulamaya singleton pattern denir
         */
    }
    static WebDriver driver;//driver object  //default constructor'i private yapmak yerine bu kismi private yaparak da obje olusumunun onune gecebilirdik.

    public static WebDriver getDriver() { //@Before gibi
        if (driver == null){ //driver'a deger atanmamis ise driver empty ise
            /*
            Driver'i her çağırdığımızda yeni bir pencere açılmasının önüne geçmek için
            if bloğu içinde Eğer driver'a değer atanmamışsa yani driver bos ise ona değer ata(bu degeri ata:
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); ,eğer değer atanmışsa
            Driver'i aynı sayfada return et.
             */
            //since driver is null the part below will be help. After driver.quit(); driver will be empty and this part will help the driver
            switch (ConfigReader.getProperty("browser")){  //browser'i burada key olarak girdik
                case "chrome": //eger browser'in degeri chrome ise ilk break'e kadarki kismi calistir diyoruz
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge": //eger browser degeri edge ise buradaki break'e kadarki kismi calistir diyoruz.
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }
    public static void closeDriver(){ //@After gibi
        if (driver != null){//-->driver'a değer ATANMIŞSA, driver in used. If driver is used close the driver
            driver.close(); //Farkli sayfalara gittigimizde birden fazla driver acilmasin, bir driver uzerinde diger sayfalara da gidebilsin
            driver = null;//-->driver'ı kapattıktan sonra boşalt. Boylece WebDriverManager.chromedriver ve devami kodlarimiz tekrar calissin
        }
        //driver.close() desek dahi bellekte hala deger atanmis gibi gorunuyor. Dolayisiyla driver'i tekrar bosaltmamiz gerekir(driver=null;)

    }
    public static void quitDriver(){
        if (driver != null){//-->driver'a değer ATANMIŞSA
            driver.quit();
            driver = null;//-->driver'ı kapattıktan sonra boşalt
        }
    }
    //Singleton driver means we have a static driver(driver must be static) and we have a public method to initiliaze driver, so whenever we say Driver.getDriver(),
    //same driver will be used accross the framework
    //When ever Sigleton Driver class instantiated, this Driver class will return the same driver intance again and again.
    //We use static keyword in our Driver Class, getDriver() method and closeDriver() method. They are static

}

