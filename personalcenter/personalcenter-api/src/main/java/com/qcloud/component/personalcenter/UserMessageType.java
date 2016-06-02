package com.qcloud.component.personalcenter;

public enum UserMessageType {
    //
    INSIDE_LETTER(1, "站内信");

    private final int    key;

    private final String name;

    private UserMessageType(int key, String name) {

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
