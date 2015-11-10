package cn.org.cfpamf.data.bus;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/20 14:47
 * 修改人：Administrator
 * 修改时间：2015/10/20 14:47
 * 修改备注：
 */
public class RecordingEvent {
    private String filePath;

    public RecordingEvent(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
