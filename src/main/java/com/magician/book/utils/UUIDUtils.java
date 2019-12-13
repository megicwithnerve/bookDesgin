package com.magician.book.utils;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UUIDUtils {
    public static String getTimerCode(String filename)
    {
        int begin = filename.lastIndexOf(".");
        String fix = filename.substring(begin, filename.length());
        System.out.println(fix);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sdf.format(new Date());

        String name = format + Integer.toHexString(filename.hashCode()) + fix;


        return name;
    }
}