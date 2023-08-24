package techproed.tests.day28_DataProvider;

import org.testng.annotations.Test;
import techproed.utilities.DataProviderUtil;

public class C05_DataProviderUtilKullanimi {

    @Test(dataProvider = "sehirVerileri",dataProviderClass = DataProviderUtil.class)
    public void test01(String sehir, String bolge, String plaka) {
        System.out.println(sehir+ " | "+bolge+" | "+plaka);
    }
    //methodumuz icine uc parametre girdik. Boylece bu uc parametre icin verileri DataProvider'dan alacagiz. Bunun icin @Test notasyonu
    //sonrasi dataProvider ve onden sonra isim olarak sehirVerileri girecegiz ve bu isme tiklarsak bu classimizda object method olusur. Bunun yerine sehirVerileri
    //ismi sonrasina virgul koyup devaminda bir class ismini yazarsak ornegin dataProviderClass=DataProviderUtil yazarsak objemiz DataProviderUtil class'inda olusacak
}