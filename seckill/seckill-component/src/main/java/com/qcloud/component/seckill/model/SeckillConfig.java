package com.qcloud.component.seckill.model;

public class SeckillConfig {

    private int    screeningsMinSize;

    private int    payMinutes;

    private String toKillString;

    private String killingString;

    private String killedString;

    private String killName;

    public int getScreeningsMinSize() {

        return screeningsMinSize;
    }

    public void setScreeningsMinSize(int screeningsMinSize) {

        this.screeningsMinSize = screeningsMinSize;
    }

    public int getPayMinutes() {

        return payMinutes;
    }

    public void setPayMinutes(int payMinutes) {

        this.payMinutes = payMinutes;
    }

    public String getToKillString() {

        return toKillString;
    }

    public void setToKillString(String toKillString) {

        this.toKillString = toKillString;
    }

    public String getKillingString() {

        return killingString;
    }

    public void setKillingString(String killingString) {

        this.killingString = killingString;
    }

    public String getKilledString() {

        return killedString;
    }

    public void setKilledString(String killedString) {

        this.killedString = killedString;
    }

    public String getKillName() {

        return killName;
    }

    public void setKillName(String killName) {

        this.killName = killName;
    }
}
