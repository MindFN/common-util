package com.util.math.sort.impl;

import com.util.math.sort.SortMethod;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

/**
 * @Author: wulang
 * @Date: 2017年10月13日 14:47
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class BuddleSort implements SortMethod {

    private static class BuddleSortHolder {
        private static final SortMethod INSTANCE = new BuddleSort();
    }

    public static SortMethod getInstance() {
        return BuddleSortHolder.INSTANCE;
    }

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> sortWithKey(Map<K, V> map, Comparator<K> comparator) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> sortWithValue(Map<K, V> map, Comparator<V> comparator) {
        return null;
    }
}
