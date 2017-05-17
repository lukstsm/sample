package com.bbt.sampleproject.data;


public interface DataCallback<T> {

    void onSuccess(final T deliverable);

    void onFailure(final String error);

}
