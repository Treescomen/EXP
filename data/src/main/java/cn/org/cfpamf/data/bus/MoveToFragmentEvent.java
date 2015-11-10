package cn.org.cfpamf.data.bus;

import android.app.Fragment;

/**
 * 项目名称：MoveToFragmentEvent
 * 类描述：
 * 创建人：zzy
 * 创建时间：2015/8/14 20:01
 * 修改人：Administrator
 * 修改时间：2015/8/14 20:01
 * 修改备注：
 */
public final class MoveToFragmentEvent {
	/**
	 * {@link android.app.Fragment} to switch.
	 */
	private Fragment mFragment;

	/**
	 * Constructor of {@link MoveToFragmentEvent}
	 *
	 * @param _fragment
	 * 		{@link android.app.Fragment} to switch.
	 */
	public MoveToFragmentEvent(Fragment _fragment) {
		mFragment = _fragment;
	}

	/**
	 * Get the {@link android.app.Fragment} to switch.
	 *
	 * @return {@link android.app.Fragment} to switch.
	 */
	public Fragment getFragment() {
		return mFragment;
	}
}
