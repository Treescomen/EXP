package cn.org.cfpamf.data.bus;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/9/23 16:37
 * 修改人：Administrator
 * 修改时间：2015/9/23 16:37
 * 修改备注：
 */
public class ProgressEvent implements Parcelable {

    private int currentProgress;
    private int totalProgress;
    private String fileName;

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(int totalProgress) {
        this.totalProgress = totalProgress;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.currentProgress);
        dest.writeInt(this.totalProgress);
        dest.writeString(this.fileName);
    }

    public ProgressEvent() {
    }

    protected ProgressEvent(Parcel in) {
        this.currentProgress = in.readInt();
        this.totalProgress = in.readInt();
        this.fileName = in.readString();
    }

    public static final Parcelable.Creator<ProgressEvent> CREATOR = new Parcelable.Creator<ProgressEvent>() {
        public ProgressEvent createFromParcel(Parcel source) {
            return new ProgressEvent(source);
        }

        public ProgressEvent[] newArray(int size) {
            return new ProgressEvent[size];
        }
    };
}
