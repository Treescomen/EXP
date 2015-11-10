/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.org.cfpamf.data;

import android.app.Application;

import com.alibaba.sdk.android.oss.util.OSSLog;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import butterknife.ButterKnife;
import cn.org.cfpamf.data.exception.CrashHandler;
import cn.org.cfpamf.data.oss.OSSUtil;

/**
 * Android Main Application
 */
public class CusApplication extends Application {
    /**
     * true debug 模式
     */
    public static boolean isDebug = true;
    /**
     * logger tag
     */
    public static final String LOGGER_TAG = "zzy";

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {
            OSSLog.enableLog();
            ButterKnife.setDebug(isDebug);
        } else {
            //关闭本地debug本地调试模式，用于测试人员反馈日志
            initCrashHandler();
        }
        initLogger();
    }

    private CrashHandler crashHandler;

    /**
     * 初始化crashHandler观察者日志
     */
    public void initCrashHandler() {
        crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    /**
     * 初始化配置Logger
     */
    private void initLogger() {
        Settings settings = Logger.init(LOGGER_TAG)               // default PRETTYLOGGER or use just init()
                .setMethodCount(2).hideThreadInfo();            // default 2
        if (isDebug)
            settings.setLogLevel(LogLevel.NONE);
        else
            settings.setLogLevel(LogLevel.FULL);
    }

}