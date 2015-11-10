package cn.org.cfpamf.data.i;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 11:04
 * 修改人：Administrator
 * 修改时间：2015/10/16 11:04
 * 修改备注：
 */
public interface IOkHttpResponse {

    void onFailed(@NonNull Exception exception);

    void onSuccess(@NonNull Response response) throws IOException;
}
