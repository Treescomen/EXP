package cn.org.cfpamf.data.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import cn.org.cfpamf.data.ApiService;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 12:49
 * 修改人：Administrator
 * 修改时间：2015/11/10 12:49
 * 修改备注：
 */
public class StartServiceManager {

    public static void startBaiduOkHttp(Context context) {
        startIntent(context, BaiduTestOkHttp.TYPE_BAIDU, null);
    }

    private static void startIntent(@NonNull Context context, @NonNull String intentType, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setType(intentType);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startService(intent);
    }
}
