package com.travel.hackroads.net;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.travel.hackroads.Bean;
import com.travel.hackroads.Result;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit mRetrofit;
    private RoadsServiceApi mService;

    private static HttpManager mManager;

    public static HttpManager getInstance() {
        if (mManager == null) {
            synchronized (HttpManager.class) {
                if (mManager == null) {
                    mManager = new HttpManager();
                }
            }
        }
        return mManager;
    }

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    return chain.proceed(builder.build());
                }
            }).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private HttpManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(RoadsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mService = mRetrofit.create(RoadsServiceApi.class);
    }

    public void requestRoads(Consumer<Result> consumer, Bean bean) {
        String data = new Gson().toJson(bean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                data);

        mService.getRoads(requestBody)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

}
