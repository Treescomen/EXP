package cn.org.cfpamf.data.oss;

import android.util.SparseArray;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.callback.DeleteCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.Range;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSData;
import com.orhanobut.logger.Logger;

/**
 * @author: zhouzhuo<yecan.xyc@alibaba-inc.com>
 * Apr 2, 2015
 */
public class OssGetAndUploadData {

    public static final String TYPE_OSS = "OssGetAndUploadData";
    public static final String OSS_ENTITY_KEY = "oss_photo_entity_key";


    private static OSSService ossService;
    private static OSSBucket bucket;

    private static OssGetAndUploadData getAndUploadData;

    public static OssGetAndUploadData getInstance() {
        ossService = OSSUtil.ossService;
        bucket = ossService.getOssBucket(OSSUtil.bucketName); // 替换为你的bucketName

        // 可选地进行访问权限或者CDN加速域名的设置
        // bucket.setBucketACL(AccessControlList.PUBLIC_READ);

        // 设置CDN加速域名时， 权限至少是公共读
        // bucket.setCdnAccelerateHostId("<cname.to.cdn.domain.com>");

        // 可选地进行Bucket cname的设置
        // bucket.setBucketHostId("<cname.to.bucket>");
        if (getAndUploadData == null) {
            synchronized (OssGetAndUploadData.class) {
                getAndUploadData = new OssGetAndUploadData();
            }
        }
        return getAndUploadData;
    }

    // 同步上传数据
    public void syncUpload(byte[] dataToUpload, String ossKey) {
        OSSData data = ossService.getOssData(bucket, ossKey);
        data.setData(dataToUpload, "image/jpg");
        // 直接从数据流上传
        // InputStream is = new ByteArrayInputStream(dataToUpload);
        // data.setInputstream(is, dataToUpload.length, "text/txt");
        try {
            data.upload();
            Logger.d("upload finish!");
        } catch (OSSException e) {
            OssHandleException.handleException(e);
        }
    }
//
//    private ProgressEvent progressBus = new ProgressEvent();
//    private SuccessEvent success = new SuccessEvent();
//
//    // 异步上传数据
//    public void asyncUpload(@NonNull final PhotoInfo photoInfo, @NonNull final Context context) {
//
//        if (!FileUtils.isFileExist(photoInfo.getPhotoPath())) {
//            success.setIsSuccess(false);
//            success.setFileName(photoInfo.getFileName());
//            success.setFailMessage("本地图片不存在");
//            OfflineBusinessManager.getInstance(context).updateOfflineBusiness(success, photoInfo);
//            EventBus.getDefault().post(success);
//            return;
//        }
//        byte[] bytes = null;
//        try {
//            bytes = de.greenrobot.common.io.FileUtils.readBytes(new File(photoInfo.getPhotoPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        OSSData data = ossService.getOssData(bucket, photoInfo.getFileName());
//        data.setData(bytes, "image/jpg");
//        // 直接从数据流上传
//        data.uploadInBackground(new SaveCallback() {
//            @Override
//            public void onSuccess(String s) {
//                success.setIsSuccess(true);
//                success.setFileName(s);
//                OfflineBusinessManager.getInstance(context).updateOfflineBusiness(success, photoInfo);
//                EventBus.getDefault().post(success);
//            }
//
//            @Override
//            public void onProgress(String s, int i, int i1) {
//                progressBus.setCurrentProgress(i);
//                progressBus.setTotalProgress(i1);
//                progressBus.setFileName(s);
//                EventBus.getDefault().post(progressBus);
//            }
//
//            @Override
//            public void onFailure(String s, OSSException e) {
//                success.setIsSuccess(false);
//                success.setFileName(s);
//                success.setFailMessage(OssHandleException.handleException(e));
//                OfflineBusinessManager.getInstance(context).updateOfflineBusiness(success, photoInfo);
//                EventBus.getDefault().post(success);
//            }
//        });
//    }

    // 上传数据的同时附加自定义meta数据
    public void uploadWithMeta(byte[] dataToUpload, String ossKey, SparseArray<String> stringSparseArray, SaveCallback saveCallback) {
        OSSData data = ossService.getOssData(bucket, ossKey);
        data.setData(dataToUpload, "text/txt");

        // 直接从数据流上传
        // InputStream is = new ByteArrayInputStream(dataToUpload);
        // data.setInputstream(is, dataToUpload.length, "text/txt");
        // 只对上传操作有效，且必须在上传前调用才生效
        // 自定义的meta属性必须以"x-oss-meta-"为前缀
        // 不支持同名的meta属性键值对
        for (int i = 0; i < stringSparseArray.size(); i++) {
            int key = stringSparseArray.keyAt(i);
            data.addXOSSMetaHeader("x-oss-meta-key" + 1, stringSparseArray.get(key));
        }
        data.uploadInBackground(saveCallback);
    }

    // 同步获取数据
    public byte[] syncGetData(String osskey) {
        OSSData data = ossService.getOssData(bucket, osskey);
        byte[] bData = null;
        try {
            bData = data.get();
            Logger.d("finish getting data! length: " + bData.length);
        } catch (OSSException e) {
            OssHandleException.handleException(e);
        }
        return bData;
    }

    // 同步获取指定范围的数据
    public byte[] getDataWithRange(String osskey) {
        OSSData data = ossService.getOssData(bucket, osskey);
        data.setRange(0, 9);
        byte[] bData = null;
        try {
            bData = data.get();
            Logger.d("finish getting data! length: " + bData.length);
        } catch (OSSException e) {
            OssHandleException.handleException(e);
        }
        return bData;
    }

    // 同步获取指定起点为范围的数据
    public void getDataRangeWithRightOpen(String osskey) {
        OSSData data = ossService.getOssData(bucket, osskey);
        data.setRange(10, Range.INFINITE);
        try {
            byte[] bData = data.get();
            Logger.d("finish getting data! length: " + bData.length);
        } catch (OSSException e) {
            OssHandleException.handleException(e);
        }
    }
//
//    // 异步获取数据
//    public void asyncGetData(Context context, String osskey, GetBytesCallback getBytesCallback) {
//        if (SharePreferencesUtils.getBoolean(context, SharePreferencesUtils.IS_WIFI_DOWN)) {
//            if (NetWorkUtils.isWifiConnected(context)) {
//                OSSData data = ossService.getOssData(bucket, osskey);
//                // 同样也可以设置范围
//                // data.setRange(10, Range.INFINITE);
//                data.getInBackground(getBytesCallback);
//            }else{
//                EventBus.getDefault().post(new NetStatusEvent(NetStatusEvent.Only_WIFI_Can_Dwon_Photo));
//            }
//            return;
//        }
//        OSSData data = ossService.getOssData(bucket, osskey);
//        // 同样也可以设置范围
//        // data.setRange(10, Range.INFINITE);
//        data.getInBackground(getBytesCallback);
//    }


    // 同步删除数据
    public void syncDelete(String osskey) {
        OSSData data = ossService.getOssData(bucket, osskey);
        try {
            data.delete();
            Logger.d("delete finish!");
        } catch (OSSException e) {
            OssHandleException.handleException(e);
        }
    }

    // 异步删除数据
    public void asyncDelete(String ossKey, DeleteCallback deleteCallback) {
        OSSData data = ossService.getOssData(bucket, ossKey);
        data.deleteInBackground(deleteCallback);
    }
}