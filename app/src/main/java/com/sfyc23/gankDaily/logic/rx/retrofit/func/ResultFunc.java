package com.sfyc23.gankDaily.logic.rx.retrofit.func;


import com.sfyc23.gankDaily.base.utils.json.JsonConvert;
import com.sfyc23.gankDaily.logic.rx.retrofit.HttpResult;

import rx.functions.Func1;

public class ResultFunc<T> implements Func1<String, HttpResult<T>> {
    @Override
    public HttpResult<T> call(String result) {
        JsonConvert<HttpResult<T>> convert = new JsonConvert<HttpResult<T>>() {};
        return convert.parseData(result);
    }
}
