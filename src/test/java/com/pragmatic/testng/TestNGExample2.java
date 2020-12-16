package com.pragmatic.testng;

import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestNGExample2 {


    @BeforeClass
    public void beforeClass() {
        System.out.println("TestNGExample1.beforeClass");
    }


    @AfterClass
    public void afterClass() {
        System.out.println("TestNGExample1.afterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("TestNGExample1.beforeMethod");
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("TestNGExample1.afterMethod");
    }


    @Test(successPercentage = 90)
    public void testMethod1() {
        System.out.println("TestNGExample1.testMethod1");
        Assert.assertTrue(true);
        Assert.assertTrue(false);
        Assert.assertTrue(true);
        Assert.assertTrue(false);
    }

    @Test
    public void testMethod2() {
        System.out.println("TestNGExample1.testMethod2");
    }

    @Test
    public void testMethod3() {
        System.out.println("TestNGExample1.testMethod3");
    }

    @Test
    public void testMethod4() {
        System.out.println("TestNGExample1.testMethod4");
    }

    @Test
    public void testMethod5() {
        System.out.println("TestNGExample1.testMethod5");
    }


}
