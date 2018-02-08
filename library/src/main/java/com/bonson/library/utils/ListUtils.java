package com.bonson.library.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <E> List<E> filters(List<E> list, ListFilter<E> listFilter) {
        if (list == null || list.isEmpty() || listFilter == null) {
            return list;
        }
        List<E> filters = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            E temp = list.get(i);
            boolean filter = listFilter.filter(i, list.get(i));
            if (filter) {
                filters.add(temp);
            }
        }
        return filters;
    }


    public interface ListFilter<E> {
        boolean filter(int index, E e);
    }

    public interface Convert<E, M> {
        M map(E e);
    }
}
