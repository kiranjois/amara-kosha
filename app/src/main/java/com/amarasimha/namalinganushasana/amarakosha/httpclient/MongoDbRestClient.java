package com.amarasimha.namalinganushasana.amarakosha.httpclient;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class MongoDbRestClient {

    //TODO Move all these to a config file
    private static final String MONGOLAB_BASE_URL = "https://api.mongolab.com/api/1";
    private static final String AMARAKOSHA_WORD_COLLN_URL = MONGOLAB_BASE_URL + "/databases/amarakosha/collections/kosha";
    private static final String MONGOLAB_API_KEY = "Kf0LLOJfI4wT7vTQsHnBYrh32da7UgsF";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        params.add("apiKey", MONGOLAB_API_KEY);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        params.add("apiKey", MONGOLAB_API_KEY);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return AMARAKOSHA_WORD_COLLN_URL + relativeUrl;
    }

}
