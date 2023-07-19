package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;


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
    public WebElement logoutButton; //Logout yazisinin locate'ini aldik. a ve i taglari ile locate aldik. Bu locate'i aldiktan sonra C03_Page classinda bu logOut'a click yaptik

    @FindBy(xpath = "//h2")
    public WebElement logoutVerify;

}