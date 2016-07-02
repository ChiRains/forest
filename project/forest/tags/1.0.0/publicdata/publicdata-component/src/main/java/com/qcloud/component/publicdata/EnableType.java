package com.qcloud.component.publicdata;
public enum EnableType {
    ENABLE(1, "启用"), DISABLE(2, "禁用");
    private final int    key;
    private final String name;

    private EnableType(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
