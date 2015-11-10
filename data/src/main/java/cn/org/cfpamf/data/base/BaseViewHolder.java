package cn.org.cfpamf.data.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 项目名称：Mts_android
 * 类描述：
 * 创建人：zhangzhanyong
 * 创建时间：2015/7/29 13:48
 * 修改人：Administrator
 * 修改时间：2015/7/29 13:48
 * 修改备注：
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
    }
}
