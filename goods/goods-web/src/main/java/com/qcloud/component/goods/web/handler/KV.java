package com.qcloud.component.goods.web.handler;

import com.qcloud.component.publicdata.IntKeyValue;

public class KV implements IntKeyValue {

    private long   k;

    private String v;

    public KV(long key, String value) {

        super();
        this.k = key;
        this.v = value;
    }

    @Override
    public long getKey() {

        return k;
    }

    @Override
    public String getValue() {

        return v;
    }
}