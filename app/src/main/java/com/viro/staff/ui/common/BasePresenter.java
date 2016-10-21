package com.viro.staff.ui.common;


import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T extends BaseView> implements Presenter<T> {

    private WeakReference<T> view;

    public BasePresenter(T view) {
        this.view = new WeakReference<T>(view);
    }

    @Nullable
    public T getView() {
        return this.view.get();
    }
}
