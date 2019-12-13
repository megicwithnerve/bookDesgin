package com.magician.book.utils;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils
{
  static String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

  public static void main(String[] args)
  {
    String a = StringInMd5("aabbcc123");
    System.out.println(a);
  }

  public static String StringInMd5(String str)
  {
    MessageDigest md5 = null;
    try
    {
      md5 = MessageDigest.getInstance("md5");
      byte[] result = md5.digest(str.getBytes());

      StringBuilder sb = new StringBuilder(32);
      for (int i = 0; i < result.length; i++)
      {
        byte x = result[i];

        int h = 0xF & x >>> 4;

        int l = 0xF & x;
        sb.append(chars[h]).append(chars[l]);
      }
      return sb.toString();
    }
    catch (NoSuchAlgorithmException e)
    {
      throw new RuntimeException(e);
    }
  }
}
