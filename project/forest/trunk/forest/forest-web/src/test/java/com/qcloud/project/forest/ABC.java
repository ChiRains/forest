package com.qcloud.project.forest;

import java.util.ArrayList;
import java.util.List;

public class ABC {

    public static void main(String[] args) {

        String[] as = new String[] { "1", "2", "3"};
        String[] bs = new String[] { "a", "b", "d", "d"};
        String[] cs = new String[] { "x", "y", "z"};
        String[] ds = new String[] { "M", "N"};
        List<String[]> ls = new ArrayList<String[]>();
        ls.add(as);
        ls.add(bs);
        ls.add(cs);
        ls.add(ds);
        com(ls);
    }

    public static void com(List<String[]> ls) {

        for (int i = ls.size() - 1; i > 0; i--) {
            String[] stringList = ls.get(i);
            //
            for (int j = 0; j < stringList.length; j++) {
                if (i - 1 >= 0) {
                    String[] twoList = ls.get(i - 1);
                    //
                    for (int k = 0; k < twoList.length; k++) {
                        if (i - 2 >= 0) {
                            String[] threeList = ls.get(i - 2);
                            //
                            for (int m = 0; m < threeList.length; m++) {
                                if (i - 3 >= 0) {
                                    String[] fourList = ls.get(i - 3);
                                    //
                                    for (int n = 0; n < fourList.length; n++) {
                                        System.out.println(stringList[j] + "---" + twoList[k] + "---" + threeList[m] + "---" + fourList[n]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("\n\n\n");
        // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
}
