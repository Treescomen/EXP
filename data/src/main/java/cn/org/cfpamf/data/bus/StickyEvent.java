package cn.org.cfpamf.data.bus;

/**
 * 项目名称：StickyEvent
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/8/14 20:01
 * 修改人：Administrator
 * 修改时间：2015/8/14 20:01
 * 修改备注：
 */
public class StickyEvent {
    private Object object;

    public StickyEvent(Object _object) {
        object = _object;
    }

    public Object getDataObject() {
        return object;
    }
}
