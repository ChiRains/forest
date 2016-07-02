package com.qcloud.component.orderform.model;

import com.qcloud.component.orderform.AutoChangeStateCondition;
import com.qcloud.component.orderform.AutoChangeTimeLimit;

@SuppressWarnings("rawtypes")
public class AutoChangeDefination {

    private int                      sourceState;

    private int                      destState;

    private int                      type;

    private int                      timeSplit;

    private AutoChangeTimeLimit      autoChangeTimeLimit;

    private AutoChangeStateCondition autoChangeStateCondition;

    public int getSourceState() {

        return sourceState;
    }

    public void setSourceState(int sourceState) {

        this.sourceState = sourceState;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public int getTimeSplit() {

        return timeSplit;
    }

    public void setTimeSplit(int timeSplit) {

        this.timeSplit = timeSplit;
    }

    public AutoChangeTimeLimit getAutoChangeTimeLimit() {

        return autoChangeTimeLimit;
    }

    public void setAutoChangeTimeLimit(AutoChangeTimeLimit autoChangeTimeLimit) {

        this.autoChangeTimeLimit = autoChangeTimeLimit;
    }

    public int getDestState() {

        return destState;
    }

    public void setDestState(int destState) {

        this.destState = destState;
    }

    public AutoChangeStateCondition getAutoChangeStateCondition() {

        return autoChangeStateCondition;
    }

    public void setAutoChangeStateCondition(AutoChangeStateCondition autoChangeStateCondition) {

        this.autoChangeStateCondition = autoChangeStateCondition;
    }
}
