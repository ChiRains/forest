package com.qcloud.component.sellercenter;

import com.qcloud.component.publicdata.IntKeyValue;

public enum DistributionType implements IntKeyValue {
    YES(1, "是"), NO(2, "否");

    private final int    key;

    private final String value;

    private DistributionType(int key, String value) {

        this.key = key;
        this.value = value;
    }

    public long getKey() {

        return key;
    }

    @Override
    public String getValue() {

        return value;
    }
}
