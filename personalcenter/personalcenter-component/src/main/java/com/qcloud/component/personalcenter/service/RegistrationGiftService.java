package com.qcloud.component.personalcenter.service;

public interface RegistrationGiftService {

    boolean sendGift(Long userId);

    Long getCouponId();
}
