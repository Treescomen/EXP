package cn.org.cfpamf.data.response.base;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/9/15 17:23
 * 修改人：Administrator
 * 修改时间：2015/9/15 17:23
 * 修改备注：
 */
public class ResponseStatus implements Parcelable {

    private String errorCode;
    private String message;
    private List<ErrorResponse> errors;

    public ResponseStatus(String errorCode, String message, List<ErrorResponse> errors) {
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResponse> errors) {
        this.errors = errors;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.errorCode);
        dest.writeString(this.message);
        dest.writeTypedList(errors);
    }

    protected ResponseStatus(Parcel in) {
        this.errorCode = in.readString();
        this.message = in.readString();
        this.errors = in.createTypedArrayList(ErrorResponse.CREATOR);
    }

    public static final Creator<ResponseStatus> CREATOR = new Creator<ResponseStatus>() {
        public ResponseStatus createFromParcel(Parcel source) {
            return new ResponseStatus(source);
        }

        public ResponseStatus[] newArray(int size) {
            return new ResponseStatus[size];
        }
    };
}
