package com.bbt.sampleproject.presentation.presenter;


import com.bbt.sampleproject.presentation.view.BaseView;

public abstract class BasePresenter<V extends BaseView> {

    private final V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    public V getView() {
        return mView;
    }


}
