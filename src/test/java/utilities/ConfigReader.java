package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    /*
        .properties uzantili dosyaya erişebilmek için Properties class'ından obje oluşturmamız gerekir.
     bu oluşturduğumuz obje ile akışa aldığımız dosya yolunu properties.load(fis) methodu ile properties
     dosyasındaki bilgileri objemize yükler ve properties dosyasındaki key değerini return ederiz.
     Bunu yapmak için parametreli bir method oluşturur girdiğimiz key'in değerini bize döndürür
     */
    static Properties properties; //.properties uzantili dosyaya erisebilmemiz icin bu objeyi olusturduk.

    static { //static block her methoddan once calisir. Burada getProperty methodu her cagrildiginda tekrar tekrar ayni islemler yapilmasin diye  bir static block bu
        //sekilde olusturuldu. Bu bellekten tasarruf saglayacak.
        try {
            FileInputStream fis = new FileInputStream("configuration.properties");//Biz configuration.properties'i bir dosyaYolu ismini verecegimiz bir String'e assign
            //etseydik FileInputStream parantezi icerisine dosyaYolu yazardik
            properties = new Properties(); //yukarida olusturdugumuz objemize atama yaptik.
            properties.load(fis);//-->fis'in okuduğu bilgileri properties'e yükler ve dosyadaki key degeri return eder
        } catch (IOException e) { //FileNotFoundExeption yerine daha kapsayici oln IOException'i yazdik
            throw new RuntimeException(e); //baslangicta bu try catch blogu getProperty methodumuz icersinde idi. Ancak daha sonra static block yapinca bu kismi static block icine
            //aldik ve her getProperty methodu cagrildiginda bu kismin once calismasi saglanacak.
        }
    }
    public static String getProperty(String key) {//getProperty() yazinca icersinde String key olacagi yazili halde goruluyor. Bu String parametreyi girecegiz

        return properties.getProperty(key);//-->String olarak girdiğim key'in değerini return eder
    }

}