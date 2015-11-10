package cn.org.cfpamf.data.okHttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.UUID;

import cn.org.cfpamf.data.database.DatabaseManager;
import cn.org.cfpamf.data.sql.db.Baidu;
import de.greenrobot.event.EventBus;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 11:00
 * 修改人：Administrator
 * 修改时间：2015/11/10 11:00
 * 修改备注：
 */
public class BaiduTestOkHttp extends AbstractBaseOkHttp {

    public static final String TYPE_BAIDU = "BaiduTestOkHttp";

    /**
     * @param context
     * @param bundle
     */
    public BaiduTestOkHttp(@NonNull Context context, @NonNull Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public AbstractBaseOkHttp getPostEvent() {
        return this;
    }

    @Override
    public String getUrl() {
        return "Http://www.baidu.com";
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrl()).build();
    }

    /**
     * 没有body
     *
     * @return
     */
    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public void onSuccess(@NonNull Response response) {
        try {
            String strResponse = response.body().string();
            Baidu baiu = new Baidu();
            baiu.setId(UUID.randomUUID().toString());
            baiu.setResponse(strResponse);
            //插入数据库
            new DatabaseManager<Baidu>(context).insert(baiu);
            //通知前台更新
            EventBus.getDefault().post(baiu);
            Logger.d(strResponse);
        } catch (IOException e) {
            onFailed(e);
        }
    }
}
