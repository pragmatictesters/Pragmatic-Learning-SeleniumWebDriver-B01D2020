package com.pragmatic.examples.java;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class SplitStringExample {


    public static void main(String[] args) {


        String user_names = "Accepted usernames are:\n" +
                "standard_user\n" +
                "locked_out_user\n" +
                "problem_user\n" +
                "performance_glitch_user";

        String[] names = user_names.split("\n");

        for (String name : names) {
            System.out.println("name = " + name);
        }


    }

}
