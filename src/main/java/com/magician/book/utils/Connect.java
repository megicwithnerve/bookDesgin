package com.magician.book.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect
{
  public static String interfaceUtil(String path, String data)
  {
    try
    {
      URL url = new URL(path);

      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      PrintWriter out = null;

      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
      conn.setDoOutput(true);
      conn.setDoInput(true);

      out = new PrintWriter(conn.getOutputStream());
      out.print(data);
      out.flush();
      InputStream is = conn.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
      String str = "";
      String miao = "";
      while ((str = br.readLine()) != null) {
        miao = miao + str;
      }
      is.close();


      conn.disconnect();

      return miao;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
