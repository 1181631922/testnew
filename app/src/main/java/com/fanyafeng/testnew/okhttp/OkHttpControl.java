package com.fanyafeng.testnew.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.service.carrier.CarrierMessagingService;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by fanyafeng on 2015/12/29,0029.
 */
public class OkHttpControl {
    private static OkHttpControl okHttpControl;
    private Handler delivetyHandler;
    private static OkHttpClient okHttpClient = null;
    private Gson mGson;

    private OkHttpControl() {
        okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(20, TimeUnit.SECONDS);
        okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        delivetyHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpControl getInstance() {
        if (okHttpControl == null) {
            synchronized (OkHttpControl.class) {
                if (okHttpControl == null) {
                    okHttpControl = new OkHttpControl();
                }
            }
        }
        return okHttpControl;
    }


}
