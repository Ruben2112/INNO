package com.inno.bourdrbij.servercommunication;

import com.loopj.android.http.*;


/**
 * Created by sebas on 5/20/2016.
 */
public class HTTPRestClient {

    private static final String BASE_URL = "http://192.168.1.168/public/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        String absoluteUrl = getAbsoluteUrl(url);
    try{
        client.get(absoluteUrl, params, responseHandler);
    }catch(Exception ex) {
        System.out.println(ex);

    }

    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.delete(getAbsoluteUrl(url), params, responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
