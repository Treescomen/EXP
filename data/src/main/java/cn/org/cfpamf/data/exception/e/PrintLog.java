package cn.org.cfpamf.data.exception.e;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.io.File;

import cn.org.cfpamf.data.constant.Constants;
import cn.org.cfpamf.data.util.FileUtils;


/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/16 14:55
 * 修改人：Administrator
 * 修改时间：2015/10/16 14:55
 * 修改备注：
 */
public class PrintLog {

    /**
     * 日志名称
     */
    private static String mtsLog = "MTSLog";

    private String url;

    private String requestBody;

    private String headers;

    private String requestTime;

    private String responseTime;

    private String responseBody;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("地址: ").append(url).append("\n")
                .append("请求头: ").append(headers).append("\n")
                .append("开始时间: ").append(requestTime).append("\n")
                .append("结束时间: ").append(responseTime).append("\n")
                .append("返回信息: ").append(responseBody).toString();
        if (requestBody != null) {
            stringBuilder.append("请求参数: ").append(requestBody).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 打印日志
     *
     * @param
     */
    public void printLogToSdCard(Context context) {
        String fileName = mtsLog + this.requestTime + ".txt";
        String messageLog = toString();
        File file = FileUtils.makeFile(Constants.APP_BASE_PATH.append(fileName).toString());
        FileUtils.writeFile(file.getAbsolutePath(), messageLog);
//        StatService.reportError(context, messageLog);
        Logger.e(messageLog.toString());
    }
}
