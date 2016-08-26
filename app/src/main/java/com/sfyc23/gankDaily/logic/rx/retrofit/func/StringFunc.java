package com.sfyc23.gankDaily.logic.rx.retrofit.func;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Func1;

public class StringFunc implements Func1<ResponseBody, String> {
    @Override
    public String call(ResponseBody responseBody) {
        String result = null;
        try {
            result = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
