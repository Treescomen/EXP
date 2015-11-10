package cn.org.cfpamf.data.i;


import cn.org.cfpamf.data.okHttp.AbstractBaseOkHttp;

/**
 * 项目名称：groupBackstage
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/10/27 15:02
 * 修改人：Administrator
 * 修改时间：2015/10/27 15:02
 * 修改备注：
 */
public interface IOkHttpEvent<M extends AbstractBaseOkHttp> {
    M getPostEvent();
}
