package cn.org.cfpamf.data.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/8/16 19:04
 * 修改人：Administrator
 * 修改时间：2015/8/16 19:04
 * 修改备注：
 */
public class SnackBarUtils {

    public static void makeSnackBarShort(Context context, View view, String message) {
        hideKeyboard(view, context);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void makeSnackBarLong(Context context, View view, String message) {
        hideKeyboard(view, context);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void makeSnackBarShortByCallBack(Context context, View view, String message, String actionMessage, View.OnClickListener onClickListener) {
        hideKeyboard(view, context);
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction(actionMessage, onClickListener).show();
    }

    public static void makeSnackBarLongByCallBack(Context context, View view, String message, String actionMessage, View.OnClickListener onClickListener) {
        hideKeyboard(view, context);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionMessage, onClickListener).show();
    }

    public static void makeSnackBarIndefiniteByCallBack(Context context, View view, String message, String actionMessage, View.OnClickListener onClickListener) {
        hideKeyboard(view, context);
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction(actionMessage, onClickListener).show();
    }
}
