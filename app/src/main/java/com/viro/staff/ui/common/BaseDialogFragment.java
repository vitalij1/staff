package com.viro.staff.ui.common;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viro.staff.annotation.LayoutId;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment<P extends Presenter> extends DialogFragment {

    private P presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = this.getClass().getAnnotation(LayoutId.class).value();
        View view = inflater.inflate(layoutId, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        presenter = createPresenter();
        presenter.onAttach(getActivity());
        super.onCreate(savedInstanceState);
    }

    public abstract P createPresenter();

    public P getPresenter() {
        return presenter;
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
