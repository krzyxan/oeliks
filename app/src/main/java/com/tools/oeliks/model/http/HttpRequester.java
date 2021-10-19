package com.tools.oeliks.model.http;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequester {

    public static String requestUrl(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        final Response response = client.newCall(request).execute();

        return Objects.requireNonNull(response.body()).string();
    }
}
