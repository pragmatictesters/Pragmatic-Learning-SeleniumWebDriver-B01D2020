package com.pragmatic.selenium.hrm;

import org.testng.annotations.DataProvider;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class HRMTestData {


    @DataProvider(name = "user-credentials")
    public static Object[][] userCredentials() {
        return new Object[][]{
                {"Admin", "", "Password cannot be empty"},
                {"Admin", "PTEst", "Invalid credentials"},
                {"", "", "Username cannot be empty"},
                {"", "Ptl@#321", "Username cannot be empty"}

        };


    }

}
