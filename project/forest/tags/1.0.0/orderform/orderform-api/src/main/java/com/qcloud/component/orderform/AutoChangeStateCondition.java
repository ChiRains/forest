package com.qcloud.component.orderform;

public interface AutoChangeStateCondition<T> {

    boolean canChange(Long id, T t);
}
