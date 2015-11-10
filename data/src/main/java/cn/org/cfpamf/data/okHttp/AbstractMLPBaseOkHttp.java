package cn.org.cfpamf.data.okHttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Response;

import cn.org.cfpamf.data.exception.e.ServerResponseException;
import cn.org.cfpamf.data.response.base.BaseServerResponse;


/**
 * Created by zzy on 15/9/19.
 * 抽象出基于服务端统一response处理
 */
public abstract class AbstractMLPBaseOkHttp extends AbstractBaseOkHttp {


    public AbstractMLPBaseOkHttp(@NonNull Context context, @NonNull Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void onSuccess(@NonNull Response response) {
        try {
            String responseString = response.body().string();
            BaseServerResponse baseServerResponse = new Gson().fromJson(responseString, BaseServerResponse.class);
            if (Boolean.valueOf(baseServerResponse.getSuccess())) {
                //成功也打印日志
                printLogger(responseString);
                //处理成功消息
                onMlpSuccess(responseString);
                Logger.d("responseString==" + responseString);
            } else {
                onFailed(new ServerResponseException(baseServerResponse.getResponseStatus().getMessage()));
            }
        } catch (Exception e) {
            onFailed(e);
        }
    }

    /**
     * 子类只处理成功
     *
     * @param responseBody
     */
    public abstract void onMlpSuccess(String responseBody);
}
