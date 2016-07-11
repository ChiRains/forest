package com.qcloud.project.forest.outside.entity;

import java.util.LinkedHashMap;
import java.util.List;

public class MessageResult extends AbstractResult {

    public FailList    failList;

    public SuccessList successList;
    //
    class FailList {

        String errMessage;

        String tradeid;
    }
    //
    class SuccessList {

        String omsid;

        String tradeid;
    }

    @SuppressWarnings("unchecked")
    public FailList getFailList() {

        List<LinkedHashMap<String, Object>> failLists = (List<LinkedHashMap<String, Object>>) ((LinkedHashMap<String, Object>) message).get("failList");
        FailList failList = new FailList();
        if (failLists.size() > 0) {
            LinkedHashMap<String, Object> failMap = failLists.get(0);
            failList.errMessage = (String) failMap.get("errMessage");
            failList.tradeid = (String) failMap.get("tradeid");
        }
        return failList;
    }

    @SuppressWarnings("unchecked")
    public SuccessList getSuccessList() {

        List<LinkedHashMap<String, Object>> successLists = (List<LinkedHashMap<String, Object>>) ((LinkedHashMap<String, Object>) message).get("successList");
        SuccessList successList = new SuccessList();
        if (successLists.size() > 0) {
            LinkedHashMap<String, Object> successMap = successLists.get(0);
            successList.omsid = (String) successMap.get("omsid");
            successList.tradeid = (String) successMap.get("tradeid");
        }
        return successList;
    }
}
