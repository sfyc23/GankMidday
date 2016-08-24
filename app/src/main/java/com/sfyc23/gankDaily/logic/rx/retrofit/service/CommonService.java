package com.sfyc23.gankDaily.logic.rx.retrofit.service;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 *
 */
public interface CommonService {
    String BASE_URL = "https://github.com/sfyc23/";//这个不重要，可以随便写，但是必须有

    @GET
    Observable<ResponseBody> loadString(@Url String url);

    @GET
    @Streaming
    Observable<ResponseBody> download(@Url String url);
}
