package cn.org.cfpamf.data.response.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/9/15 17:02
 * 修改人：Administrator
 * 修改时间：2015/9/15 17:02
 * 修改备注：
 */
public class BaseServerResponse implements Parcelable {

    private String success;
    private ResponseStatus responseStatus;
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.success);
        dest.writeParcelable(this.responseStatus, 0);
    }

    public BaseServerResponse() {
    }

    protected BaseServerResponse(Parcel in) {
        this.success = in.readString();
        this.responseStatus = in.readParcelable(ResponseStatus.class.getClassLoader());
    }

    public static final Creator<BaseServerResponse> CREATOR = new Creator<BaseServerResponse>() {
        public BaseServerResponse createFromParcel(Parcel source) {
            return new BaseServerResponse(source);
        }

        public BaseServerResponse[] newArray(int size) {
            return new BaseServerResponse[size];
        }
    };
}
