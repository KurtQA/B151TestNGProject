package techproed.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import techproed.utilities.Driver;


public class GooglePage {
    public GooglePage(){ //constructor olusturduk
        PageFactory.initElements(Driver.getDriver(),this); //PageFactory'den initElements methodu ile Driver'i buraya tanimladik
    }
    @FindBy(xpath="//div[text()='Accept all']")
    public WebElement beforeGoogle;

    @FindBy(xpath = "//*[@class='gLFyf']")
    public WebElement aramaKutusu;

}