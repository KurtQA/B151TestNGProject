package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.utilities.Driver;


public class TechproTestCenterPage {
    public TechproTestCenterPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//*[@name='username']")
    public WebElement username;

    @FindBy(xpath = "//*[@name='password']")
    public WebElement password;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@role='alert']")  //sayfaya giris yapildigini dogrulamak icin once buraya kadar kodumuzu calistirdik ve acilan sayfada You logged into a secure area!
    //yazisinin locate'ini almamiz halinde sayfaya giris yapildigini dogrulayayacagimizi dusunduk. Bunun uzerine yazinin locate'ini aldik.
    public WebElement girisVerify;

    @FindBy(xpath = "//a//i")
    public WebElement logoutButton; //Logout yazisinin locate'ini aldik. a ve i taglari ile locate aldik. Bu locate'i aldiktan sonra C03_Page classinda bu logOut'a click yaptik.
    //Bir yerde logout dogrudan gorunmuyorsa profile uzerine gelip click yapinca logout secenekler arasinda gorunebilir ve sadece o secenek uzerine gelip inspect yapariz. Ancak
    //once profile#in locate'ini aliriz ve ona click yapinca logout goruldugunden locate'ini aldigimiz profile'a click methodunu ekleriz. Daha sonra logout locate'i ve ona da
    //click methodu ekleyerek logout islemini gerceklestirmis oluruz.

    @FindBy(xpath = "//h2")
    public WebElement logoutVerify; //We check the url on the top. if Url has /auth/ keyword, it means logout is successful. We can locate this url with 'auth' keyword
    //We can assert that as follows: Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("auth"));

}