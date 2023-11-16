package com.jeebase.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Type implements Serializable {
    private static final long serialVersionUID = -1078060287799905561L;

    public void exchange(String arg1, String [] arg2)
    {
        arg1  = "i am not a";
        arg2[0]  = "r";
        arg2[1]  = "o";
        arg2[2]  = "o";
        arg2[3]  = "k";
        arg2[4]  = "i";
        arg2[5]  = "e";
    }

    public static void main(String[]args)
    {
        String i = "i am a";
        String arr[] = {"r","a","b","b","i","t"};
        new Type().exchange(i,arr);
        System.out.print(i +" "+ Arrays.toString(arr));
    }
}
