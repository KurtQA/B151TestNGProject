package techproed.utilities;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    Workbook workbook; //Workbook objemiz
    Sheet sheet; //Sheet objemiz
    public ExcelReader(String dosyaYolu,String sayfaIsmi){ //constructor(Constuctordan obje olustururken bir tanesi dosya yolu digeri sayfa ismi olacak sekilde parametreler girdik)
        try { //her constructor'i cagirdigimizda exception atmasin diye try catch blogu icerisine aldik.
            FileInputStream fis = new FileInputStream(dosyaYolu);  //excel dosyasini akisa alabildik. FileInputStream test class'inda parametre olarak neyi girersek onu okuyacak
            workbook = WorkbookFactory.create(fis); //Workbook objesini yukarida olusturduk simdi de atamasini yaptik yani initialize yaptik. WorkbookFactory.create ile excel dosyasi
            //okunacak
            sheet = workbook.getSheet(sayfaIsmi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //Satır ve sütun sayılarını girdiğimde, O hücredeki veriyi return eder
    public String getCellData(int satir,int sutun){
        Cell cell = sheet.getRow(satir).getCell(sutun); //Cell objemizi sheet.getRow.getCell'e esitledik
        return cell.toString();
    }
    //Exceldeki satır sayısını return eder
    public int rowCount(){
        return sheet.getLastRowNum();
    }
}