package com.pragmatic.examples.java;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SwitchExample {


    public static void multipleConditions(String browser){

        if (browser.isEmpty() || browser.isBlank() ){
            System.exit(-1);
        }

        switch (browser.toLowerCase()){
            case "chrome" -> {
                System.out.println("browser 1 = " + browser);
            }
            case "firefox" -> {
                System.out.println("browser 2" + browser);
            }case "firefox-headless" -> {
                System.out.println("browser 3 = " + browser);
            } default ->  {
                throw new IllegalStateException("Unexpected value: " + browser.toLowerCase());
            }
        }

    }


    public static void main(String[] args) {

        multipleConditions("firefox-headless");

    }


}
