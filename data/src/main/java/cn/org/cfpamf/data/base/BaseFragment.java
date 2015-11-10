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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import cn.org.cfpamf.data.util.ToastUtils;
import cn.org.cfpamf.data.manager.AutoGridLayoutManager;
import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment {


    public abstract View setView(LayoutInflater inflater, ViewGroup container);


    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = setView(inflater, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterView();
    }

    public abstract void afterView();

    public void intent(Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    protected void intent(Class<?> cls, Map<String, String> map) {
        Set<String> set = map.keySet();
        Intent intent = new Intent(getActivity(), cls);
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        startActivity(intent);
    }

    protected void addBackFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    // get layout manager
    public LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }
    // get layout manager
    public LinearLayoutManager getHorizontalLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    }

    // get layout manager
    public GridLayoutManager getGridLayoutManager() {
        return getGridLayoutManager(2);
    }

    // get layout manager
    public GridLayoutManager getGridLayoutManager(int spanCount) {
        return new AutoGridLayoutManager(getActivity(), spanCount);
    }


    protected void showToastMessage(String message) {
        ToastUtils.showShortMessage(message, getActivity());
    }

    protected void showToastErrorMessage(String errorMessage) {
        ToastUtils.showError(errorMessage, getActivity());
    }

    protected void showWaitForDevelop() {
        showToastMessage("功能仍在努力开发中");

    }


    protected AlertDialog dialog;

    protected AlertDialog createHintDialog(String title, String message) {
        dialog = new AlertDialog.Builder(getActivity()).setTitle(title).setMessage(message).show();
        return dialog;
    }

    protected void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        dismissDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isStickyAvailable()) {
            EventBus.getDefault().registerSticky(this);
        } else {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Handler for {@link }
     *
     * @param e Event {@link  }.
     */
    public void onEvent(Object e) {

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
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
