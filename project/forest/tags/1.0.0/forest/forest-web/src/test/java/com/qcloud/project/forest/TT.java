package com.qcloud.project.forest;

import com.qcloud.pirates.util.TableSplitUtil;

public class TT {

    /**
     * @param args
     */
    public static void main(String[] args) {

        for (int index = 0; index < 100; index++) {
            System.out.println("alter table " + TableSplitUtil.getTableSplitName(index, "commoditycenter_merchandise_evaluation", 100) + " rename to " + TableSplitUtil.getTableSplitName(index, "goods_merchandise_evaluation", 100) + ";");
        }
    }
}
