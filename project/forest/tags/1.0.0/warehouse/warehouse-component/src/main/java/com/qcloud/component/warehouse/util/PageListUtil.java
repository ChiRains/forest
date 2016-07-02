package com.qcloud.component.warehouse.util;

import java.util.ArrayList;
import java.util.List;
import com.qcloud.pirates.data.Page;

/**
 * 分页基础类
 * @author shiliew
 *
 */
public class PageListUtil {

    /**
     * 获取分页列表中对应页码数据列表
     * @param start 页码
     * @param count     每页显示数量
     * @param list      分页数据
     * @return          对应页码数据列表
     */
    @SuppressWarnings("unchecked")
    public static <T> Page<T> getPager(int pageNum, int count, List<T> list) {

        Page<T> page = new Page<T>();
        if (list.size() == 0) {
            return page;
        }
        List<T> dataList = new ArrayList<T>();
        int fromIndex = (pageNum - 1) * count;
        int toIndex = pageNum * count;
        int totalCount = list.size();
        if (toIndex > totalCount) {
            toIndex = totalCount;
        }
        for (Object o : list.subList(fromIndex, toIndex)) {
            dataList.add((T) o);
        }
        page.setData(dataList);
        page.setCount(list.size());
        return page;
    }

    /**
     * 获取分页列表中对应页码数据列表
     * @param start 页码
     * @param count     每页显示数量
     * @param list      分页数据
     * @return          对应页码数据列表
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getList(int pageNum, int count, List<T> list) {

        if (list.size() == 0) {
            return null;
        }
        List<T> dataList = new ArrayList<T>();
        int fromIndex = (pageNum - 1) * count;
        int toIndex = pageNum * count;
        int totalCount = list.size();
        if (toIndex > totalCount) {
            toIndex = totalCount;
        }
        for (Object o : list.subList(fromIndex, toIndex)) {
            dataList.add((T) o);
        }
        return dataList;
    }
}
