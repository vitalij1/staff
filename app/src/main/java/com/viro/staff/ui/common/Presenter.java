package com.viro.staff.ui.common;


import android.app.Activity;

public interface Presenter<T extends BaseView>{
    void onAttach(Activity context);
    T getView();
    void onDetach();
}
