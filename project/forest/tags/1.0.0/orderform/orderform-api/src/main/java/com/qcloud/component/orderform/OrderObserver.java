package com.qcloud.component.orderform;

public interface OrderObserver<T> {

    void doNotify(T t, int state, String[] variable);
}
