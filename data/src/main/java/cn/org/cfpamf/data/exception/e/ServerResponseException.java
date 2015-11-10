package cn.org.cfpamf.data.exception.e;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/26 14:49
 * 修改人：Administrator
 * 修改时间：2015/10/26 14:49
 * 修改备注：
 */
public class ServerResponseException extends Exception {


    public ServerResponseException() {
        super();
    }

    public ServerResponseException(final String message) {
        super(message);
    }

    public ServerResponseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ServerResponseException(final Throwable cause) {
        super(cause);
    }
}
