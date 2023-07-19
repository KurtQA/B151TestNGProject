package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


public class OpenSourcePage {
    /*
        Bir sayfanın locatelerini page class'ında tutarız. Bir constructor oluşturup locatelere ulaşabilmek için
    Pagefactory'den initelements() methodu ile driver'imizi bu class'a tanımlarız.
        findelement() methodu yerine @FindBy notasyonu ile bu notosyona parametre olarak locateleri gireriz.
     */
    public OpenSourcePage(){ //class isimli constructor
        PageFactory.initElements(Driver.getDriver(),this); //Driver class'indaki getDriver methodundaki driver burdaki driver demis oluyoruz
    }
    //https://opensource-demo.orangehrmlive.com/web/index.php/auth/login adresine gidince acilan sayfada Login altinda Username:Admin Password:admin123 oldugunu gorduk ve
    // configuration.properties dosyamizda bu URL, Username ve password'a buna gore value yazdik. Key olarak userName yerine kullaniciAdi, password yerine sifre yazdik.
    //Ayrica Login altindaki Username ve password checkbox;larin locatelerini inspect yaparak aldik
    @FindBy(xpath = "//*[@name='username']") //Onceden ogrendigimizden farkli olarak @FinfBy ve sadece xpath kullandik ayrica xpath sonrasi = koyduk
    public WebElement username; //locate'ini aldigimiz webelement'e isimlendirmeyi onceden farkli olarak ayrica ve alt bolumde yapiyoruz.

    @FindBy(xpath = "//*[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@type='submit']")// websayfasinda password altinda login butonu vardi onun da locate'ini aldik
    public WebElement loginButton;

    //Bu locateleri test class'inda kullanacagiz

    @FindBy(xpath = "//h6")  //websayfasindaki Dashboard locate'i olup HTML kodlarinda h6 tagi Dashboard icin yeterli oldu. Zaten bu bir baslik idi. Basligin locate'i aliniyorsa
    //h1, h2, h6 gibi taglara bakalim
    public WebElement verify;


}