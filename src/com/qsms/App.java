package com.qsms;

import java.io.UnsupportedEncodingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnsupportedEncodingException
    {
        String str = "\u4e0d\u652f\u6301\u6b64\u7c7b\u56fe\u7247\u4e0a\u4f20";
        String s = new String(str.getBytes(),"utf-8");
        System.out.println(s);
    }
}
