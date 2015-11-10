package cn.org.cfpamf.data.bus;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/8/31 20:40
 * 修改人：Administrator
 * 修改时间：2015/8/31 20:40
 * 修改备注：
 */
public class ThrowableLogEvent {

    private String intentType;
    private String errorMessage;

    public ThrowableLogEvent(String intentFlag, String errorMessage) {
        this.intentType = intentFlag;
        this.errorMessage = errorMessage;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
