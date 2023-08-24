package techproed.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ListenersRapor implements ITestListener,IRetryAnalyzer, IAnnotationTransformer {
    private static ExtentReports extent;
    private static ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {//@BeforeClass gibi
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        System.out.println("onStart Methodu -> Tüm testlerden önce tek bir sefer çağrılır "+context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("onFinish Methodu -> Tüm testlerden sonra tek bir sefer çağrılır "+context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {//@Before gibi
        extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        System.out.println("onTestStart Methodu -> Her bir test'ten önce tek bir sefer çağrılır "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test PASSED");
        System.out.println("onTestSuccess Methodu -> PASSED olan testlerden sonra tek bir sefer çağrılır "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail(result.getThrowable());
        try {
            extentTest.addScreenCaptureFromPath(ReusableMethods.tumSayfaResmi(result.getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("onTestFailure Methodu -> FAILED olan testlerden sonra tek bir sefer çağrılır "+result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped Methodu -> SKIP(atlanan) olan testlerden sonra tek bir sefer çağrılır "+result.getName());
    }

    private int retryCount = 0;
    private static final int maxRetryCount = 1;
    @Override
    public boolean retry(ITestResult result) {
        extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());

        if (retryCount < maxRetryCount) {
            extentTest.info("Test tekrar denendi");
            retryCount++;
            return true;
        }

        return false;
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
            Bu method sayesinde Listeners sınıfını .xml dosyasında kullanabileceğiz ve istediğimiz class'ları fail
        olma durumunda listeners sınıfı retry methodunu kullanarak istediğimiz kadar tekrar çalıştırabileceğiz.
         */
        annotation.setRetryAnalyzer(Listeners.class);
    }
}