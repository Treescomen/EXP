package cn.org.cfpamf.data.okHttp;

import android.content.Context;
import android.os.Bundle;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;


/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 11:15
 * 修改人：Administrator
 * 修改时间：2015/10/16 11:15
 * 修改备注：
 */
public abstract class AbstractMLPGetOkHttp extends AbstractMLPBaseOkHttp {

    public AbstractMLPGetOkHttp(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrlByParams()).build();
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }
    /**
     * 在地址后面加请求参数
     * @return
     */
    private String getUrlByParams() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getUrl());
        if (bundle.containsKey(BUNDLE_GET_KEY)) {
            OkHttpRequestParams okHttpRequestParams = bundle.getParcelable(BUNDLE_GET_KEY);
            if (okHttpRequestParams != null) {
                stringBuilder.append("?").append(okHttpRequestParams.toString());
            }
        }
        return stringBuilder.toString();
    }
}
