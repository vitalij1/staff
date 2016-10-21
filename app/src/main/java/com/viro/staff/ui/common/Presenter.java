package com.viro.staff.ui.common;


import android.content.Context;

public interface Presenter<T extends BaseView>{
    void onAttach(Context context);
    T getView();
    void onDetach();
}
