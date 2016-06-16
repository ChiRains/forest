package com.qcloud.component.marketing.service;

public interface RegistrationGiftService {

    boolean sendGift(Long userId);

    Long getCouponId();
}
