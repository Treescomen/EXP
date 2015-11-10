package cn.org.cfpamf.data.bus;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/8/16 19:20
 * 修改人：Administrator
 * 修改时间：2015/8/16 19:20
 * 修改备注：
 */
public class NetStatusEvent {

    public static final String Please_Try_Again_Wifi_Connection = "请连接wifi后重试";
    public static final String Please_Connect_To_The_Network_And_Try_Again = "请连接网络后重试";
    public static final String Only_WIFI_Can_Upload_Photo = "只能用无线网络提交照片";
    public static final String Only_WIFI_Can_Dwon_Photo = "只能用无线网络下载照片";

    private String message;

    public NetStatusEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
