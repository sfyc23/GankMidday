
package com.sfyc23.gankDaily.logic.rx.retrofit;

import com.sfyc23.gankDaily.logic.rx.retrofit.factory.ServiceFactory;
import com.sfyc23.gankDaily.logic.rx.retrofit.func.ResultFunc;
import com.sfyc23.gankDaily.logic.rx.retrofit.func.RetryWhenNetworkException;
import com.sfyc23.gankDaily.logic.rx.retrofit.func.StringFunc;
import com.sfyc23.gankDaily.logic.rx.retrofit.service.CommonService;
import com.sfyc23.gankDaily.logic.rx.retrofit.subscriber.DownLoadSubscribe;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ObservableProvider {
    private static volatile ObservableProvider instance = null;
    private CommonService mCommonService;
    private ObservableProvider() {
        mCommonService = ServiceFactory.getInstance().createService(CommonService.class);
    }

    public static ObservableProvider getDefault() {
        if (instance == null) {
            synchronized (ObservableProvider.class) {
                if (instance == null) {
                    instance = new ObservableProvider();
                }
            }
        }
        return instance;
    }


    public Observable<String> loadString(String url) {
        return mCommonService
                .loadString(url)
                .compose(TransformUtils.<ResponseBody>defaultSchedulers())
                .retryWhen(new RetryWhenNetworkException())
                .map(new StringFunc());
    }

    public <T> Observable<HttpResult<T>> loadResult(String url) {
        return loadString(url).map(new ResultFunc<T>());
    }

    public void download(String url, final DownLoadSubscribe subscribe) {
        mCommonService
                .download(url)
                .compose(TransformUtils.<ResponseBody>all_io())
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        subscribe.writeResponseBodyToDisk(responseBody);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        subscribe.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscribe.onError(e);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //do nothing
                    }
                });

    }


}
