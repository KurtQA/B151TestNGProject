package tests.day25_POM;

import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;


public class C02_PropertiesKullanimi {
    @Test
    public void amazonTest() {
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        //public static String getProperty(String key) { getProperty methodumuzu utilities package'indaki ConfigReader class'inda olusturmustuk. Key olarak amazonUrl'i parametre
        //olarak girdik.
        //return properties.getProperty(key);
        //amazonUrl=https://www.amazon.com/ bu datayi configuration.properties file'i icerisinde bu sekilde yazmistik. Bu nedenle amazonUrl'i parametre olarak girince
        //onun Url'i return edilecek
        /*
        Çok fazla amazon sayfasına gittiğimizi düşünelim ve amazon url'inde
      bir değişiklik olduğunda bütün test'lerden url'i düzeltmek yerine
      sadece .properties dosyasından düzelterek tüm testlerdeki url'i
      düzeltmiş oluruz. .properties uzantili dosya olusturarak temel test datalarini depolayacagiz. temel test datalarina ornek:
      url, username, password, browser,...
      Test datalari bu dosyadan bir Java class ile(ConfigReader) alinir ve test caselerde dinamik olarak kullanilir
      driver.get("www.techproeducation.com"); yerine driver.get(url);
      key, value degerleri config dosyalarinda depolanip test caselerde dinamik sekilde kullanilir.
      Key=Value
      url=https://www.techproeducation.com
      browser=chrome
      username=manager
      password=pass
      name=Ali
      Properties dosyalari genelde proje seviyesinde olusturulur. Projeye sag tikla->New->File->configuration.properties
         */

    }
}