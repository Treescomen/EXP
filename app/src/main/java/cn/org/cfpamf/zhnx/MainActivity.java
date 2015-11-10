package cn.org.cfpamf.zhnx;

import android.widget.TextView;

import butterknife.Bind;
import cn.org.cfpamf.data.base.BaseActivity;
import cn.org.cfpamf.data.manager.StartServiceManager;
import cn.org.cfpamf.data.okHttp.BaiduTestOkHttp;
import cn.org.cfpamf.data.sql.db.Baidu;

public class MainActivity extends BaseActivity {

    @Bind(R.id.text_response)
    TextView text_response;

    @Override
    protected void setToolbar() {
    }

    @Override
    protected void setLayoutContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void afterView() {
        StartServiceManager.startBaiduOkHttp(this);
    }

    /**
     * 处理失败信息
     *
     * @param baiduTestOkHttp
     */
    public void onEventMainThread(BaiduTestOkHttp baiduTestOkHttp) {
        createHintDialog("失败", baiduTestOkHttp.getErrorMessage());
    }

    /**
     * 处理失败信息
     * @param baidu
     */
    public void onEventMainThread(Baidu baidu) {
        //更新UI
        text_response.setText(baidu.getResponse());
    }
}
