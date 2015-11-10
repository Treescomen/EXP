/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.org.cfpamf.data.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import cn.org.cfpamf.data.R;
import cn.org.cfpamf.data.bus.NetStatusEvent;
import cn.org.cfpamf.data.manager.AppManager;
import cn.org.cfpamf.data.util.ToastUtils;
import cn.org.cfpamf.data.manager.AutoGridLayoutManager;
import de.greenrobot.event.EventBus;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 最先执行
     */
    protected abstract void setToolbar();


    /**
     * 执行在setToolbar()之后
     */
    protected abstract void setLayoutContentView();

    /**
     * 执行在setLayoutContentView() 之后
     */
    protected abstract void afterView();

    protected Toolbar toolbar;

    private ProgressDialog progressDialog;
    private AlertDialog dialog;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initToolbar();
        setToolbar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        setLayoutContentView();
        ButterKnife.bind(this);
        afterView();
    }

    protected Toolbar initToolbar() {
        if (toolbar == null) {
//            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                toolbar.setNavigationIcon(R.drawable.icon_back);
                // Sub Title
//        include_toolbar.setSubtitle("Sub title");
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
        return toolbar;
    }

    protected void setToolbar(String title) {
        if (toolbar == null) {
            Logger.e("include_toolbar==null");
            return;
        }
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setToolbar(int title) {
        setToolbar(getString(title));
    }

    protected Toolbar getToolbar() {
        return this.toolbar;
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container itemView to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void intent(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void intentForResult(Class<?> cls, Map<String, String> map, int requestCode) {
        Set<String> set = map.keySet();
        Intent intent = new Intent(this, cls);
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        startActivityForResult(intent, requestCode);
    }

    protected void intent(Class<?> cls, Map<String, String> map) {
        Set<String> set = map.keySet();
        Intent intent = new Intent(this, cls);
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        startActivity(intent);
    }

    protected void intentForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    // get layout manager
    public LinearLayoutManager getVerticalLinearLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    // get layout manager
    public GridLayoutManager getGridLayoutManager(int spanCount) {
        return new AutoGridLayoutManager(getApplicationContext(), spanCount);
    }


    protected void showToastMessage(String message) {
        ToastUtils.showShortMessage(message, this);
    }

    protected void showToastErrorMessage(String errorMessage) {
        ToastUtils.showError(errorMessage, this);
    }

    protected void showWaitForDevelop() {
        showToastMessage("功能仍在努力开发中");
    }

    /**
     * Is the {@link android.app.Fragment} ready to subscribe a sticky-event or not.
     *
     * @return {@code true} if the {@link android.app.Fragment}  available for sticky-events inc. normal events.
     * <p/>
     * <b>Default is {@code false}</b>.
     */
    protected boolean isStickyAvailable() {
        return false;
    }

    @Override
    protected void onResume() {
        if (isStickyAvailable()) {
            EventBus.getDefault().registerSticky(this);
        } else {
            EventBus.getDefault().register(this);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        dismissDialog();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    protected AlertDialog createHintDialog(String title, String message) {
        dialog = new AlertDialog.Builder(this).setTitle(title).setMessage(message).show();
        return dialog;
    }

    protected void updateHintDialog(String title, String message) {
        if (dialog == null) return;
        dialog.setTitle(title);
        dialog.setMessage(message);
    }

    protected AlertDialog createHintDialogForLocation(String title, String message, DialogInterface.OnClickListener onClickListener) {
        dialog = new AlertDialog.Builder(this).setTitle(title).setMessage(message).setNegativeButton("返回并保存", onClickListener).setPositiveButton("取消", onClickListener).show();
        return dialog;
    }

    protected AlertDialog createHintDialogForLogin(String title, String message) {
        dialog = getDialog(title, message);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    protected AlertDialog createListDialogForChoose(String title, int itemChecked, String[] tagList, DialogInterface.OnClickListener onClickListener) {
        dialog = new AlertDialog.Builder(this).setTitle(title).setSingleChoiceItems(tagList, itemChecked, onClickListener).show();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private AlertDialog getDialog(String title, String message) {
        if (dialog == null) {
            synchronized (AlertDialog.class) {
                dialog = new AlertDialog.Builder(this).setTitle(title).setMessage(message).show();
            }
        } else {
            if (dialog.isShowing()) {
                dialog.setTitle(title);
                dialog.setMessage(message);
            } else {
                dismissDialog();
                createHintDialog(title, message);
            }
        }
        return dialog;
    }

    protected void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    protected ProgressDialog createProgressDialog(String title, String message) {
        // 进度条还有二级进度条的那种形式，这里就不演示了
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        progressDialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        if (!progressDialog.isShowing())
            progressDialog.show();
        return progressDialog;
    }

    protected void updateProgressDialog(int max, int progress) {
        if (progressDialog == null) return;
        progressDialog.setMax(max);
        progressDialog.setProgress(progress);
    }

    protected LocationManagerProxy mLocationManagerProxy;

    /**
     * 初始化定位
     */
    protected void initLocation(AMapLocationListener aMapLocationListener) {

        mLocationManagerProxy = LocationManagerProxy.getInstance(this);

        //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
        //在定位结束后，在合适的生命周期调用destroy()方法
        //其中如果间隔时间为-1，则定位只定一次
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 60 * 1000, 15, aMapLocationListener);

        mLocationManagerProxy.setGpsEnable(true);
    }

    protected void stopLocation(AMapLocationListener aMapLocationListener) {
        if (mLocationManagerProxy != null) {
            mLocationManagerProxy.removeUpdates(aMapLocationListener);
            mLocationManagerProxy.destory();
        }
        mLocationManagerProxy = null;
    }

    public void onEventMainThread(NetStatusEvent netStatusEvent) {
        showToastErrorMessage(netStatusEvent.getMessage());
    }
}
