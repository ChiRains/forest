package com.qcloud.component.brokerage.model.query;

public class UserRelationshipQuery {

    private String account;

    private Long   recommedId;

    public UserRelationshipQuery() {

    }

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {

        this.account = account;
    }

    public Long getRecommedId() {

        return recommedId;
    }

    public void setRecommedId(Long recommedId) {

        this.recommedId = recommedId;
    }
}
