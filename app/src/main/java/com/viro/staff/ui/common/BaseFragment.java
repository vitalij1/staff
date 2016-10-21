package com.viro.staff.ui.common;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viro.staff.annotation.LayoutId;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends Presenter> extends Fragment {

    private P presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = this.getClass().getAnnotation(LayoutId.class).value();
        View view = inflater.inflate(layoutId, container, false);
        ButterKnife.bind(this, view);
        presenter = createPresenter();
        return view;
    }

    @Override
    public void onResume() {
        presenter.onAttach(getActivity());
        super.onResume();
    }

    public abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onPause() {
        presenter.onDetach();
        super.onPause();
    }
}
