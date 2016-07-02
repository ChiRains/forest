import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.qcloud.component.publicdata.IntKeyValue;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {

//        IntKeyValue[] as = new IntKeyValue[] { new KV(10, "1"), new KV(11, "2"), new KV(12, "3")};
//        IntKeyValue[] bs = new IntKeyValue[] { new KV(20, "a"), new KV(21, "b"), new KV(22, "c"), new KV(23, "d"), new KV(24, "e")};
//        IntKeyValue[] cs = new IntKeyValue[] { new KV(30, "x"), new KV(31, "y"), new KV(32, "z")};
//        IntKeyValue[] ds = new IntKeyValue[] { new KV(40, "M"), new KV(41, "N")};
//        IntKeyValue[] es = new IntKeyValue[] { new KV(50, "A"), new KV(51, "B"), new KV(52, "C")};
     
        IntKeyValue[] as = new IntKeyValue[] { new KV(10, "1"), new KV(11, "2"), new KV(12, "3")};
        IntKeyValue[] bs = new IntKeyValue[] { new KV(20, "a"), new KV(21, "b"), new KV(22, "c"), new KV(23, "d"), new KV(24, "e")};
        IntKeyValue[] cs = new IntKeyValue[] { new KV(30, "x"), new KV(31, "y"), new KV(32, "z")};
        IntKeyValue[] ds = new IntKeyValue[] { new KV(40, "M"), new KV(41, "N")};
        IntKeyValue[] es = new IntKeyValue[] { new KV(50, "A"), new KV(51, "B"), new KV(52, "C")};
    
        
        List<IntKeyValue[]> ls = new ArrayList();
        ls.add(as);
        ls.add(bs);
        ls.add(cs);
//        ls.add(ds);
//        ls.add(es);
        com(ls);
    }

    public static void com(List<IntKeyValue[]> ls) {

        int size = 0;
        for (IntKeyValue[] str : ls) {
            size = size == 0 ? 1 : size;
            size = size * str.length;
        }
        IntKeyValue[][] strs = new IntKeyValue[size][];
        for (int index = 0; index < size; index++) {
            strs[index] = new IntKeyValue[ls.size()];
        }
        int[] indexs = new int[ls.size()];
        for (int i : indexs) {
            indexs[i] = 0;
        }
        for (int index = 0; index < size; index++) {
            IntKeyValue[] ss = strs[index];
            for (int i = 0; i < ss.length; i++) {
                ss[i] = ls.get(i)[indexs[i]];
            }
            indexs[indexs.length - 1] = indexs[indexs.length - 1] + 1;
            add(indexs, ls);
        }
        List<IntKeyValue[]> result = new ArrayList<IntKeyValue[]>();
        for (IntKeyValue[] strings : strs) {
            result.add(strings);
        }
        Collections.sort(result, new Comparator<IntKeyValue[]>() {

            @Override
            public int compare(IntKeyValue[] o1, IntKeyValue[] o2) {

                for (int index = 0; index < o1.length; index++) {
                    int r = o1[index].getValue().compareTo(o2[index].getValue());
                    if (r != 0) {
                        return r;
                    }
                }
                return 0;
            }
        });
        for (IntKeyValue[] strings : result) {
            for (IntKeyValue str : strings) {
                System.out.print(str.getValue());
                System.out.print("--");
            }
            System.out.println();
        }
    }

    private static void add(int[] indexs, List<IntKeyValue[]> ls) {

        for (int i = indexs.length - 1; i >= 0; i--) {
            if (indexs[i] == ls.get(i).length) {
                indexs[i] = 0;
                if (i > 0) {
                    indexs[i - 1] = indexs[i - 1] + 1;
                }
                add(indexs, ls);
            }
        }
    }
    static class KV implements IntKeyValue {

        private long   k;

        private String v;

        public KV(long key, String value) {

            super();
            this.k = key;
            this.v = value;
        }

        @Override
        public long getKey() {

            return k;
        }

        @Override
        public String getValue() {

            return v;
        }
    }
}
