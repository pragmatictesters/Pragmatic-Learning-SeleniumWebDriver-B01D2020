package com.pragmatic.examples.java;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.NumberFormat;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class DiscountCalculationFromStringValues {


    @Test
    public void testDiscount() {
        String price = "1,150";
        String originalPrice = "3,450";
        String displayedDiscount = "-67%";

        float priceValue = Float.parseFloat(price.replace(",", "").trim());
        float originalValue = Float.parseFloat(originalPrice.replace(",", "").trim());
        float discount = (priceValue - originalValue) / originalValue;
        NumberFormat percentage = NumberFormat.getPercentInstance();
        percentage.setMaximumFractionDigits(0);

        System.out.println("discount = " + percentage.format(discount));

        Assert.assertEquals(percentage.format(discount), displayedDiscount);


    }

}
