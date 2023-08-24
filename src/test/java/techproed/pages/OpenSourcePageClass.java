package techproed.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import techproed.utilities.Driver;


public class OpenSourcePageClass {
    //Burada constructor kullanmadan static bir method ile username'in locate'ini return edecegiz.
    //OpenSourcePage class'i icerisinde once bir constructor olusturmustuk. driver'i tanimlamistik ve @FindBy ile locate almistik. Burada ise
    //static methodlarla dogrudan locate'i return ettik. En sik karsilacagimiz @FindBy ile locate alma olacak
    //username locate
    public static WebElement username(){
        return Driver.getDriver().findElement(By.xpath("//*[@name='username']"));
    }

    //password locate
    public static WebElement password(){
        return Driver.getDriver().findElement(By.xpath("//*[@name='password']"));
    }

    //loginButton locate
    public static WebElement loginButton(){
        return Driver.getDriver().findElement(By.xpath("//*[@type='submit']"));
    }

    //verify locate
    public static WebElement verify(){
        return Driver.getDriver().findElement(By.xpath("//h6"));
    }


}