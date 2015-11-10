package cn.org.cfpamf.data.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

public class ViewUtils {
    public static class ViewAttr {
        public int width;
        public int height;
    }

    protected static int widthScreen, heightScreen;

    public static Point getScreen(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        widthScreen = dm.widthPixels;
        heightScreen = dm.heightPixels;
        return new Point(widthScreen, heightScreen);
    }

    public static ViewAttr getViewAttr(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        ViewAttr viewAttr = new ViewAttr();
        viewAttr.width = width;
        viewAttr.height = height;
        return viewAttr;
    }

    public static int dip2px(Context context, String dipValue) {
        if (dipValue.endsWith("dip")) {
            dipValue = dipValue.substring(0, dipValue.length() - 3);
        }
        float value = Float.valueOf(dipValue);
        return dip2px(context, value);
    }

    public static int px2dip(Context context, String dipValue) {
        if (dipValue.endsWith("dip")) {
            dipValue = dipValue.substring(0, dipValue.length() - 3);
        }
        float value = Float.valueOf(dipValue);
        return px2dip(context, value);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static Rect getScreen(Activity activity) {
        Rect r = new Rect();
        activity.getWindow().peekDecorView().getWindowVisibleDisplayFrame(r);
        return r;
    }
}
