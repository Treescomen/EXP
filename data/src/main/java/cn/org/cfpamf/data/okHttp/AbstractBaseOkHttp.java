package cn.org.cfpamf.data.okHttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import cn.org.cfpamf.data.exception.e.HandleOkHttpException;
import cn.org.cfpamf.data.exception.e.PrintLog;
import cn.org.cfpamf.data.i.IOkHttpEvent;
import cn.org.cfpamf.data.i.IOkHttpRequest;
import cn.org.cfpamf.data.i.IOkHttpResponse;
import cn.org.cfpamf.data.util.TimeUtils;
import de.greenrobot.event.EventBus;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/20 14:03
 * 修改人：Administrator
 * 修改时间：2015/10/20 14:03
 * 修改备注：
 */
public abstract class AbstractBaseOkHttp implements IOkHttpRequest, IOkHttpResponse, IOkHttpEvent {

    private static final String CONTENT_TYPE_KEY = "Content-Type";
    protected static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final String ACCEPT_KEY = "Accept";
    private static final String ACCEPT = "application/json";
    private static final String UTF8 = "utf8";

    private static OkHttpClient okHttpClient;
    /**
     * get请求参数 Bundle key
     */
    public static final String BUNDLE_GET_KEY = "BUNDLE_GET_KEY";
    /**
     * post/put 请求参数 Bundle key
     */
    public static final String BUNDLE_POST_OR_PUT_KEY = "BUNDLE_POST_OR_PUT_KEY";

    protected Context context;
    protected Bundle bundle;
    protected Request request;

    /**
     * 日志
     */
    protected PrintLog printLog;
    ;
    /**
     * 网络请求 异常信息
     */
    protected String errorMessage;

    /**
     * @param context
     * @param bundle
     */
    public AbstractBaseOkHttp(@NonNull Context context, @NonNull Bundle bundle) {
        this.context = context;
        this.bundle = bundle;
        printLog = new PrintLog();
        printLog.setRequestTime(TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒")));
        request = getRequest();
        printLog.setUrl(request.urlString());
        printLog.setHeaders(new StringBuilder().append(CONTENT_TYPE_KEY).append("=").append(request.header(CONTENT_TYPE_KEY)).toString());
        execute(getOkHttpClient(), getRequest(), getResponseCallBack());
    }

    /**
     * 获取HttpClient
     *
     * @return
     */
    @Override
    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        } else {
            okHttpClient = okHttpClient.clone();
        }
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        return okHttpClient;
    }

    /**
     * 请求配置
     *
     * @return
     */
    @Override
    public Request.Builder getRequestBuilder() {
        return new Request.Builder()
                .addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE)
                .addHeader(ACCEPT_KEY, ACCEPT);
    }

    /**
     * 处理接口返回
     * @return
     */
    @Override
    public Callback getResponseCallBack() {
        return new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                onFailed(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful())
                    onSuccess(response);
                else
                    throw new IOException();
            }
        };
    }

    /**
     * 如果子类需要处理失败信息 重写该方法
     *
     * @param exception
     */
    @Override
    public void onFailed(@NonNull Exception exception) {
        errorMessage = HandleOkHttpException.handleMessage(exception);
        printLogger(errorMessage);
        //通知前台更新 失败后返回子类对象 在Activity里注册子类的监听
        EventBus.getDefault().post(getPostEvent());
        Logger.e(errorMessage);
    }

    /**
     * 启动网络请求
     *
     * @param okHttpClient
     * @param request
     * @param callback
     */
    @Override
    public void execute(OkHttpClient okHttpClient, Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * 打印日志，写入sd卡并提交腾讯统计服务
     *
     * @param body
     */
    protected void printLogger(String body) {
        printLog.setResponseTime(TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒")));
        printLog.setResponseBody(body);
        printLog.printLogToSdCard(context);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
