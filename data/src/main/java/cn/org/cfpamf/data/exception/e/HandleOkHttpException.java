package cn.org.cfpamf.data.exception.e;


/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 14:54
 * 修改人：Administrator
 * 修改时间：2015/10/16 14:54
 * 修改备注：
 */
public class HandleOkHttpException {
    public static String handleMessage(Exception e) {
        String s = e.toString();
        if (s.contains("SocketTimeoutException")) {
            return "网络连接超时，请检查网络";
        } else if (s.contains("ConnectException")) {
            return "请求地址无效";
        } else if (s.contains("ServerResponseException")) {
            return e.getMessage();
        }
        return s;
    }
}
