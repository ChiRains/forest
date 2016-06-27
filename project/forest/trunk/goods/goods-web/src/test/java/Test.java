import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String[] as = new String[] { "1", "2", "3"};
        String[] bs = new String[] { "a", "b", "c", "d", "e"};
        // String[] bs = new String[] { "a", "b", "c", "d"};
        String[] cs = new String[] { "x", "y", "z"};
        String[] ds = new String[] { "M", "N"};
        String[] es = new String[] { "A", "B", "C"};
        List<String[]> ls = new ArrayList();
        ls.add(as);
        ls.add(bs);
        ls.add(cs);
        ls.add(ds);
        ls.add(es);
        com(ls);
    }

    public static void com(List<String[]> ls) {

        int size = 0;
        for (String[] str : ls) {
            size = size == 0 ? 1 : size;
            size = size * str.length;
        }
        String[][] strs = new String[size][];
        for (int index = 0; index < size; index++) {
            strs[index] = new String[ls.size()];
        }
        int[] indexs = new int[ls.size()];
        for (int i : indexs) {
            indexs[i] = 0;
        }
        for (int index = 0; index < size; index++) {
            String[] ss = strs[index];
            for (int i = 0; i < ss.length; i++) {
                ss[i] = ls.get(i)[indexs[i]];
            }
            indexs[indexs.length - 1] = indexs[indexs.length - 1] + 1;
            add(indexs, ls);
        }
        // for (String[] str : ls) {
        // for (int j = 0; j < str.length; j++) {
        // for (int index = 0; index < size; index++) {
        // if (index % str.length == j) {
        // strs[index][ls.indexOf(str)] = str[j];
        // }
        // }
        // }
        // }
        List<String[]> result = new ArrayList<String[]>();
        for (String[] strings : strs) {
            result.add(strings);
        }
        Collections.sort(result, new Comparator<String[]>() {

            @Override
            public int compare(String[] o1, String[] o2) {

                for (int index = 0; index < o1.length; index++) {
                    int r = o1[index].compareTo(o2[index]);
                    if (r != 0) {
                        return r;
                    }
                }
                return 0;
            }
        });
        for (String[] strings : result) {
            for (String str : strings) {
                System.out.print(str);
                System.out.print("--");
            }
            System.out.println();
        }
    }

    private static void add(int[] indexs, List<String[]> ls) {

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
}
