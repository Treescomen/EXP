package cn.org.cfpamf.data.bus;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/9/23 17:41
 * 修改人：Administrator
 * 修改时间：2015/9/23 17:41
 * 修改备注：
 */
public class SuccessEvent implements Parcelable {
    private boolean isSuccess;
    private String failMessage;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    @Override
    public String toString() {
        return "Success{" +
                "isSuccess=" + isSuccess +
                ", failMessage='" + failMessage + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(isSuccess ? (byte) 1 : (byte) 0);
        dest.writeString(this.failMessage);
        dest.writeString(this.fileName);
    }

    public SuccessEvent() {
    }

    protected SuccessEvent(Parcel in) {
        this.isSuccess = in.readByte() != 0;
        this.failMessage = in.readString();
        this.fileName = in.readString();
    }

    public static final Parcelable.Creator<SuccessEvent> CREATOR = new Parcelable.Creator<SuccessEvent>() {
        public SuccessEvent createFromParcel(Parcel source) {
            return new SuccessEvent(source);
        }

        public SuccessEvent[] newArray(int size) {
            return new SuccessEvent[size];
        }
    };
}
