package com.magician.book.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil
{
  public static String getStrackTrace(Throwable throwable)
  {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    try
    {
      throwable.printStackTrace(pw);
      return sw.toString();
    }
    finally
    {
      pw.close();
    }
  }
}

