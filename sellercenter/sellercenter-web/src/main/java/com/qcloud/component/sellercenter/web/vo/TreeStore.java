package com.qcloud.component.sellercenter.web.vo;

import com.qcloud.component.publicdata.util.TreeUtils.TreeModel;
import com.qcloud.component.sellercenter.QStore;

public class TreeStore implements TreeModel {

    private QStore store;

    public TreeStore(QStore store) {

        super();
        this.store = store;
    }

    @Override
    public long getId() {

        return store.getId();
    }

    @Override
    public long getParentId() {

        if (store.getParent() == null) {
            return -1;
        }
        return store.getParent().getId();
    }

    @Override
    public String getBsid() {

        return "";
    }

    @Override
    public String getName() {

        return store.getName();
    }
}
