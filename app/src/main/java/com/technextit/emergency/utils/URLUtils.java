package com.technextit.emergency.utils;


public class URLUtils {
    public static final String BASE_URL = "http://www.mocky.io/v2/";

    public static final String URL_SERVICES = "5598f53554469c9400218f9b";
    public static final String URL_DIVISIONS = "5598f58754469c9f00218f9c";
    /*public static final String URL_CONTACTS = "559ed3e66384a6821a728566";*/
    public static final String URL_CONTACTS = "55a0bbccd66d56e2020e9338";

    public static String getAbsoluteUrl(String url){
        return BASE_URL + url;
    }
}
