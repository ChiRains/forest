package com.qcloud.component.goods;

import java.util.Date;

public interface QMerchandiseEvaluation {

    public long getId();

    public String getContent();

    public int getStar();

    public Date getTime();

    public int getStatus();

    public String getSpecifications();

    public long getUserId();

    public String getMerchandiseName();
}
