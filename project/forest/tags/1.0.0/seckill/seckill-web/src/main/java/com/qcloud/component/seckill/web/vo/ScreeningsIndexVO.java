package com.qcloud.component.seckill.web.vo;

public class ScreeningsIndexVO extends ScreeningsVO {

    private long                 hours;

    private long                 minutes;

    private long                 seconds;

    private long                 millsMinutes;

    private MerchandiseSeckillVO merchandiseSeckillVO = new MerchandiseSeckillVO();

    public MerchandiseSeckillVO getMerchandiseSeckillVO() {

        return merchandiseSeckillVO;
    }

    public void setMerchandiseSeckillVO(MerchandiseSeckillVO merchandiseSeckillVO) {

        this.merchandiseSeckillVO = merchandiseSeckillVO;
    }

    public long getHours() {

        return hours;
    }

    public void setHours(long hours) {

        this.hours = hours;
    }

    public long getMinutes() {

        return minutes;
    }

    public void setMinutes(long minutes) {

        this.minutes = minutes;
    }

    public long getSeconds() {

        return seconds;
    }

    public void setSeconds(long seconds) {

        this.seconds = seconds;
    }

    public long getMillsMinutes() {

        return millsMinutes;
    }

    public void setMillsMinutes(long millsMinutes) {

        this.millsMinutes = millsMinutes;
    }
}
