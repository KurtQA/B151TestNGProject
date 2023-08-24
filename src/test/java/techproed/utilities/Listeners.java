package techproed.utilities;

import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Listeners implements ITestListener,IRetryAnalyzer, IAnnotationTransformer {
    /*
    Listeners; TestNG de bir test'in durumunu ve sonucunu izleyen ve bu duruma yanıt veren bir yapıdır.
    Testlerin passed ve failed olma durumlarını, başlangıç ve bitişini takip eder ve raporlayabilir.
        Bunun için TestNG den ITestListener arayüzünü(interface) kullanırız. Oluşturduğumuz Listeners
    class'ına ITestListener arayüzündeki methodları Override etmek için implements ederiz
    Listeners class is like a TestBase class
    Implement ITestListener
    Add the ITestListener Methods
    Listeners are used to Listen the test classes when they pass, fail, skip, start, end,...
     */

    //private static ExtentReports extent;
    //private static ExtentTest extentTest;
    @Override //implements yaptigimiz icin override edebildik methodlari
    public void onStart(ITestContext context) {//@BeforeSuite gibi
        //extent=new ExtentReports();
        //ExtentHTmlReporter extentHtmlReporter = new ExtentHtmlReporter(path: "extent.html")
        //extent.attacReporter(extentHtmlReporter);
        //Bunlari burada yaptigimizda html raporlamayi en basta yaptirmis oluruz
        System.out.println("onStart Methodu -> Tüm testlerden önce tek bir sefer çağrılır "+context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {//@AfterSuite gibi
        //extent.flush() ekleyebiliriz
        System.out.println("onFinish Methodu -> Tüm testlerden sonra tek bir sefer çağrılır "+context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {//@Before gibi
        //extentTest=extent.createTest(testName:"lkhjgs", description: "kjlhgtm");
        //extentTest  buraya yazarsak her testten once bu kisim baslayacak ve test gecerse
        //onTestSuccess() methodu altinda extentTest.pass(details: "PASSED"); yazabiliriz. Biz
        //her test basinda cagrilan onTestStart() methodunu kullanarak testin sonucuna gore o test
        //icinde dogrudan extentTest.pass() gibi asama hakkinda bilgi verebiliriz
        System.out.println("onTestStart Methodu -> Her bir test'ten önce tek bir sefer çağrılır "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {//Only if test pass. Execute ONCE after EACH PASSING test cases(@Test)

        System.out.println("onTestSuccess Methodu -> PASSED olan testlerden sonra tek bir sefer çağrılır "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) { //You can add take screen shoot method
        //Execute ONCE after EACH Failed test cases(@Test)
        System.out.println("onTestFailure Methodu -> FAILED olan testlerden sonra tek bir sefer çağrılır "+result.getName());
        ReusableMethods.tumSayfaResmi(result.getName());
    }
    /*
    onTestFailure() method ici soyle de olabilir( fail olursa raporuma resim ekle):
    sout sonrasi:
    try{
    extentTest.addScreenCaptureFromPath(ReusableMethods.tumSayfaResmi(result.getName()));
    }catch (IOException e) {
    throw new Runtime Exception(e);
    }
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped Methodu -> SKIP olan testlerden sonra tek bir sefer çağrılır "+result.getName());
    }

    private static int retryCount = 0;
    private static final int maxRetryCount = 1;
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage:execute ONCE based on total test success percentage: "+result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("onTestFailedWithTimeout: execute ONCE after Each failed test case due to a TIMEOUT ISSUE: "+result.getName());
    }
    /*
        Bu method sadece FAIL olan test case'leri tekrar çalıştırır
        maxRetryCount ek olarak çalisma sayısıdır. Bu örnekte Fail olan test (maxRetryCount = 1) normal bir kere
        çalıştıktan sonra fail olursa 1 kez daha çalışacaktır.
         */

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        /*
            Bu methodun amacı; test notasyonlarını, sınıfları, cons.ları ve methodları transform(dönüştürme) etmemize
        olanak sağlar
            Bu method sayesinde @Test annotation a sahip methodları yapılandırıp testin başarısız olma durumunda
            otomatik olarak testleri retry methodunda belirttiğimiz sayı miktarınca tekrar çalıştıracaktır.
         */
        annotation.setRetryAnalyzer(Listeners.class);
    }
}