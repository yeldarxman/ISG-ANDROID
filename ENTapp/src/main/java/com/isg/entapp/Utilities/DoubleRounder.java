package com.isg.entapp.Utilities;

/**
 * Created by yeldar on 13.07.13.
 */
public class DoubleRounder {
    public static double round(double value, int precision){
        int temp = (int)Math.pow(10, precision);

        int tempValue = (int)(value * temp);
        double result = ((double)tempValue)/temp;

        return result;
    }
}
