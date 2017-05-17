package com.bbt.sampleproject.presentation.view;


public interface PlayerView extends BaseView {

    void setAvatar(String url);

    void setName(String name);

    void setBalance(double balance);

    void setLastLoginDate(String date);

    void hideLastLoginDate();
}
