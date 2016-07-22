package com.qcloud.project.forest.web.outside.eximpl;

import java.util.List;

public class InvokeConfig {

    // oms接口的方法
    public String               omsToMethod;

    // 内部处理调用方法
    public String               invoke;

    // 方法参数
    public List<Class<?>>       paramTypes;

    public String getOmsToMethod() {

        return omsToMethod;
    }

    public void setOmsToMethod(String omsToMethod) {

        this.omsToMethod = omsToMethod;
    }

    public String getInvoke() {

        return invoke;
    }

    public void setInvoke(String invoke) {

        this.invoke = invoke;
    }

    public List<Class<?>> getParamTypes() {

        return paramTypes;
    }

    public void setParamTypes(List<Class<?>> paramTypes) {

        this.paramTypes = paramTypes;
    }
}
