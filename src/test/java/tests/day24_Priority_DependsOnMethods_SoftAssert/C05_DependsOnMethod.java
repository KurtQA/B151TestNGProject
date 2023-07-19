package tests.day24_Priority_DependsOnMethods_SoftAssert;

import org.testng.annotations.Test;

public class C05_DependsOnMethod {
    @Test
    public void test01() {
        System.out.println("erol");
    }
    @Test(dependsOnMethods = "test01")
    public void test02() {
        System.out.println("evren");
    }
    @Test(dependsOnMethods = "test02")
    public void test03() {
        System.out.println("esen");
    }
    @Test(dependsOnMethods = "test03")
    public void test04() {
        System.out.println("mehmet");

    }
    /*
    Console'da sirasiyla Default Suite, B151TestNGProject, test01, test02, test03, test04 ve yine sirasyla erol, evren, esen, mehmet gorduk



     */
}