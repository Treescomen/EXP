package cn.org.cfpamf.data;

import android.app.IntentService;
import android.content.Intent;

import cn.org.cfpamf.data.bus.NetStatusEvent;
import cn.org.cfpamf.data.factory.OkHttpFactory;
import cn.org.cfpamf.data.util.NetWorkUtils;
import de.greenrobot.event.EventBus;

/**
 * 项目名称：Zhnx
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/11/10 10:42
 * 修改人：Administrator
 * 修改时间：2015/11/10 10:42
 * 修改备注：
 */
public class ApiService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ApiService() {
        super("ApiService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (!NetWorkUtils.isNetworkConnected(getApplicationContext())) {
            //在baseActivity里注册即可
            EventBus.getDefault().post(new NetStatusEvent(NetStatusEvent.Please_Connect_To_The_Network_And_Try_Again));
        } else {
            OkHttpFactory.createHttp(this, intent.getType(), intent.getExtras());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
