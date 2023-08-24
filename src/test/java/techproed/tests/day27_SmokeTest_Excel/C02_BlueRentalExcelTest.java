package techproed.tests.day27_SmokeTest_Excel;

import org.testng.annotations.Test;
import techproed.utilities.ExcelReader;


public class C02_BlueRentalExcelTest {
    @Test
    public void test01() { //excel'i kullanabilmek icin en basta dependency'e ihtiyac duyariz. Apache poi library. Datalari excelden Reusable class'indaki methodlari kullaniriz.
        //mysmoketestdata excel dosyasındaki 1. satır bilgilerini konsola yazdıralım
        String dosyaYolu = "src/test/java/techproed/resources/mysmoketestdata.xlsx";  //mysmoketestdata.xlsx dosyasi
        String sayfaIsmi = "customer_info"; //mysmoketestdata.xlsx dosyasini actik alt tarafta sayfa ismi yerinde sheet1 ya da sayfa1 yerine customer_info yazili
        ExcelReader reader = new ExcelReader(dosyaYolu,sayfaIsmi);  //ExcelReader constructor'i ile ExcelReader objesi olusturduk.
        System.out.println("Birinci Email = "+reader.getCellData(1, 0));
        System.out.println("Birinci Password = "+reader.getCellData(1, 1));
    }
}