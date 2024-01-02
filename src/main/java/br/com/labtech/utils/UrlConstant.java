package br.com.labtech.utils;

public final class UrlConstant {

  private UrlConstant() {

  }

  private static String ABSOLUTE_URL;

  public static void setAbsoluteUrl(String url) {
    ABSOLUTE_URL = url;
  }

  public static String getAbsoluteUrl() {
    return ABSOLUTE_URL;
  }
}
