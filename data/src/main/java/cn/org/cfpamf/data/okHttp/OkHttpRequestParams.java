package cn.org.cfpamf.data.okHttp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 11:27
 * 修改人：Administrator
 * 修改时间：2015/10/16 11:27
 * 修改备注：
 */
public class OkHttpRequestParams implements Parcelable {

    protected ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();

    /**
     * 添加
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    /**
     * 添加
     *
     * @param key
     * @param value
     */
    public void put(String key, int value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    /**
     * 添加
     *
     * @param key
     * @param value
     */
    public void put(String key, long value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    /**
     * 移除
     *
     * @param key
     */
    public void remove(String key) {
        urlParams.remove(key);
    }

    /**
     * 是否存在
     *
     * @param key
     * @return
     */
    public boolean has(String key) {
        return urlParams.get(key) != null;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.urlParams);
    }

    public OkHttpRequestParams() {
    }

    protected OkHttpRequestParams(Parcel in) {
        this.urlParams = (ConcurrentHashMap<String, String>) in.readSerializable();
    }

    public static final Parcelable.Creator<OkHttpRequestParams> CREATOR = new Parcelable.Creator<OkHttpRequestParams>() {
        public OkHttpRequestParams createFromParcel(Parcel source) {
            return new OkHttpRequestParams(source);
        }

        public OkHttpRequestParams[] newArray(int size) {
            return new OkHttpRequestParams[size];
        }
    };
}
