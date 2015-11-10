package cn.org.cfpamf.data.response.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/9/15 17:17
 * 修改人：Administrator
 * 修改时间：2015/9/15 17:17
 * 修改备注：
 */
public class ErrorResponse implements Parcelable {

    private String errorCode;
    private String fieldName;
    private String message;

    public ErrorResponse(String errorCode, String fieldName, String message) {
        this.errorCode = errorCode;
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.errorCode);
        dest.writeString(this.fieldName);
        dest.writeString(this.message);
    }

    protected ErrorResponse(Parcel in) {
        this.errorCode = in.readString();
        this.fieldName = in.readString();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ErrorResponse> CREATOR = new Parcelable.Creator<ErrorResponse>() {
        public ErrorResponse createFromParcel(Parcel source) {
            return new ErrorResponse(source);
        }

        public ErrorResponse[] newArray(int size) {
            return new ErrorResponse[size];
        }
    };
}
