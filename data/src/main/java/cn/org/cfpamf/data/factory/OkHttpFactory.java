package cn.org.cfpamf.data.factory;

import android.content.Context;
import android.os.Bundle;

import cn.org.cfpamf.data.okHttp.AbstractBaseOkHttp;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 10:44
 * 修改人：Administrator
 * 修改时间：2015/11/10 10:44
 * 修改备注：
 */
public class OkHttpFactory {

    private static AbstractBaseOkHttp abstractBaseOkHttp;

    public static AbstractBaseOkHttp createHttp(Context context, String intentType, Bundle bundle) {
        switch (intentType) {
            case BaiduTestOkHttp.TYPE_BAIDU:
                abstractBaseOkHttp = new BaiduTestOkHttp(context, bundle);
                break;
        }
        return abstractBaseOkHttp;
    }
}
