package cn.org.cfpamf.data.constant;

import java.io.File;

import cn.org.cfpamf.data.util.ExternalStorageUtil;


/**
 * 项目名称：Mts_android
 * 类描述：
 * 创建人：zhangzhanyong
 * 创建时间：2015/7/24 10:13
 * 修改人：Administrator
 * 修改时间：2015/7/24 10:13
 * 修改备注：
 */

public class Constants {

    /**
     * 程序隐藏根目录
     */
    public static final StringBuilder APP_BASE_PATH = ExternalStorageUtil.getExternalStoragePath();
    /**
     * 程序异常崩溃日志
     */
    public static final StringBuilder APP_CRASH_BASE_PATH = new StringBuilder().append(APP_BASE_PATH).append("crash").append(File.separator);
    /**
     * 照片存储路径
     */
    public static final StringBuilder APP_IMG_FOLDER_PATH = new StringBuilder().append(APP_BASE_PATH).append("photos").append(File.separator);
    /**
     * 服务地址配置路径
     */
    public static final StringBuilder APP_URL_FOLDER_PATH = new StringBuilder().append(APP_BASE_PATH).append("URL/URL.txt");
    /**
     * 照片存储路径文件夹名
     */
    public static final StringBuilder APP_IMG_FOLDER_NAME = new StringBuilder().append("photos").append(File.separator);
    /**
     * 打开系统相机返回resultCode
     */
    public static final int CAMERA_TAKE = 11;

}
