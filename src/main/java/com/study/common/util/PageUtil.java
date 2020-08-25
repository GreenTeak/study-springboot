package com.study.common.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    /**
     * 分页一
     *
     * @param list
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;
        List<List<T>> listArray = new ArrayList<List<T>>();
        for (int i = 0; i < page; i++) {
            List<T> subList = new ArrayList<T>();
            for (int j = 0; j < listSize; j++) {
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
                if (pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if ((j + 1) == ((j + 1) * pageSize)) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }

    /**
     * 分页二
     *
     * @param list
     * @param page  相当于pageNo
     * @param count 相当于pageSize
     */
    public static <T> List<T> getListByPage(List<T> list, int page, int count) {
        // 页码不能小于等于0
        if (page <= 0) {
            page = 1;
        }
        int size = list.size();         // 数据条数
        int pageCount = size / count;   // 总共分的页数-1，当有3条数据，也数为5时，pageCount = 0;
        int fromIndex = count * (page - 1); // 需要展示的起始下标

        // 计算需要查询的结束下表
        int toIndex = fromIndex + count;//当查询的页码在中间的时候

        if (toIndex >= size) {
            toIndex = size;
        }
        // 要展示的大于总页数
        if (page > pageCount + 1) {
            fromIndex = 0;
            if (size < count) {
                toIndex = size;
            } else {
                toIndex = count;
            }
        } else if (page == pageCount + 1) {
            int lastSize = size - (page - 1) * count;
            toIndex = (page - 1) * count + lastSize;
        } else if (page < pageCount + 1) {
            toIndex = (page - 1) * count + count;
        }
        return list.subList(fromIndex, toIndex);
    }


    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 8; i++) {
            list.add("集合==" + i);
        }

        List result = getListByPage(list, 2, 5);
        System.out.println(result);


    }
}
