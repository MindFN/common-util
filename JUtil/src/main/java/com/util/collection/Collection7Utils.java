package com.util.collection;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.*;

/**
 * 工具
 *
 * @author wulang
 * @version v1.0
 * @date 2017年11月24日 15:56
 * @description
 * @modified By:
 * @modifued reason:
 */
public class Collection7Utils {

    /**
     * 将容器中的数据转换成map
     *
     * @param            <K> map.entry的key的类型
     * @param            <V> map.entry的value的类型
     * @param            <F> collection中实体类型
     * @param            <T> map中存放的实体类型
     * @param collection 容器
     * @param function   转换规则
     * @return 返回的map永远不会为null;
     */
    public static <K, V, F, T extends Map.Entry<K, V>> Map<K, V> transformCollectionToMap(Collection<F> collection,
            Function<F, T> function) {
        Map<K, V> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(collection)) {
            map = Maps.newHashMapWithExpectedSize(collection.size());
            for (F from : collection) {
                T target = function.apply(from);
                map.put(target.getKey(), target.getValue());
            }
        }
        return map;
    }

    /**
     * 拆分链表
     *
     * @param list
     * @param batch
     * @return
     * @author: wulang
     * @date: 2017/11/28 21:09
     * @modify by user: {修改人} 2017/11/28 21:09
     * @modify by reason:
     */
    public static <T> List<List<T>> splitCollection(Collection<T> list, Integer batch) {
        List<List<T>> subListCollection = Lists.newArrayList();
        int startIndex = -1;
        int nextIndex = 0;
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(list)) {
            List<T> sourceList = Lists.newArrayList(list);
            subListCollection = Lists.newArrayListWithExpectedSize(sourceList.size());
            for (startIndex = 0; startIndex < sourceList.size(); startIndex += batch) {
                nextIndex += batch;
                nextIndex = nextIndex > sourceList.size() ? sourceList.size() : nextIndex;
                subListCollection.add(sourceList.subList(startIndex, nextIndex));
            }
        }
        return subListCollection;
    }

    /**
     * 将容器中的数据转换成map 将多个的value保存的List
     *
     * @param collection  容器
     * @param keyMapper   key
     * @param valueMapper value
     * @return java.util.Map<K,java.util.List<V>>
     * @author wulang
     * @date 2018/1/17 21:03
     * @modify by user: {修改人} 2018/1/17 21:03
     * @modify by reason:
     */
    public static <K, V, T> Map<K, List<V>> transformToMapWithListValue(Collection<T> collection,
            Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper) {
        Map<K, List<V>> map = Maps.newHashMap();
        List<V> valueList = null;
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(collection)) {
            map = Maps.newHashMapWithExpectedSize(collection.size());
            for (T from : collection) {
                K k = keyMapper.apply(from);
                V v = valueMapper.apply(from);
                if (org.apache.commons.collections4.CollectionUtils.isEmpty(valueList = map.get(k))) {
                    valueList = Lists.newArrayList();
                }
                valueList.add(v);
                map.put(k, valueList);
            }
        }
        return map;
    }

    /**
     * 将容器中的数据转换成map 将多个的value保存到Set
     *
     * @param            <K> map.entry的key的类型
     * @param            <V> map.entry的value的类型
     * @param            <F> collection中实体类型
     * @param collection 容器
     * @return 返回的map永远不会为null;
     */
    public static <K, V, F> Map<K, Set<V>> transformToMapWithSetValue(Collection<F> collection,
            Function<? super F, ? extends K> keyMapper, Function<? super F, ? extends V> valueMapper) {
        Map<K, Set<V>> map = Maps.newHashMap();
        Set<V> valueSet = null;
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(collection)) {
            Iterator<F> iterator = collection.iterator();
            map = Maps.newHashMapWithExpectedSize(collection.size());
            while (iterator.hasNext()) {
                F from = iterator.next();
                K k = keyMapper.apply(from);
                V v = valueMapper.apply(from);
                if (org.apache.commons.collections4.CollectionUtils.isEmpty(valueSet = map.get(k))) {
                    valueSet = Sets.newHashSet();
                }
                valueSet.add(v);
                map.put(k, valueSet);
            }
        }
        return map;
    }

    /**
     * 将容器中的数据转换成map 将多个的value保存的Map
     * <p>
     * function 返回格式为 Map.Entry<K1, Map.Entry<K2, V>>
     *
     * @param            <K1> map.entry的key的类型
     * @param            <K2> map.entry的value的类型
     * @param            <V> map.entry的value的类型
     * @param            <F> collection中实体类型
     * @param collection 容器
     * @return 返回的map永远不会为null;
     */
    public static <K1, K2, V, F> Map<K1, Map<K2, V>> transformCollectionToMapWithMapValue(Collection<F> collection,
            Function<? super F, ? extends K1> keyMapper1, Function<? super F, ? extends K2> keyMapper2,
            Function<? super F, ? extends V> valueMapper) {
        Map<K1, Map<K2, V>> map = Maps.newHashMap();
        Map<K2, V> valueMap = null;
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(collection)) {
            map = Maps.newHashMapWithExpectedSize(collection.size());
            for (F from : collection) {
                K1 k1 = keyMapper1.apply(from);
                if (org.apache.commons.collections4.MapUtils.isEmpty(valueMap = map.get(k1))) {
                    valueMap = Maps.newHashMap();
                }
                K2 k2 = keyMapper2.apply(from);
                V v = valueMapper.apply(from);
                valueMap.put(k2, v);
                map.put(k1, valueMap);
            }
        }
        return map;
    }

    /**
     * 将map中的每个entry进行处理后的list; 将多个的value保存到Set
     *
     * @param map
     * @param function
     * @param          <T> 目标类型
     * @param          <K>
     * @param          <V>
     * @return
     */
    public static <T, K, V> Set<T> transformMapToList(Map<K, V> map, Function<Map.Entry<K, V>, T> function) {
        Map.Entry<K, V> entry = null;
        Set<T> sets = Sets.newHashSet();
        if (MapUtils.isNotEmpty(map)) {
            sets = Sets.newHashSetWithExpectedSize(map.size());
            Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                sets.add(function.apply(entry));
            }
        }
        return sets;
    }

    /**
     * 将旧的map转换成新的map
     *
     * @param fromMap
     * @param function
     * @return
     * @author: wulang
     * @date: 2017/10/9 20:34
     * @modify by user: {修改人} 2017/10/9 20:34
     * @modify by reason:
     */
    public static <K1, K2, V1, V2> Map<K2, V2> transformMapToNewMap(Map<K1, V1> fromMap,
            Function<Map.Entry<K1, V1>, Map.Entry<K2, V2>> function) {
        Map.Entry<K1, V1> entry1 = null;
        Map.Entry<K2, V2> entry2 = null;
        Map<K2, V2> newMap = Maps.newHashMap();
        if (MapUtils.isNotEmpty(fromMap)) {
            newMap = Maps.newHashMapWithExpectedSize(fromMap.size());
            Iterator<Map.Entry<K1, V1>> iterator = fromMap.entrySet().iterator();
            while (iterator.hasNext()) {
                entry1 = iterator.next();
                entry2 = function.apply(entry1);
                newMap.put(entry2.getKey(), entry2.getValue());
            }
        }
        return newMap;
    }

    public static class MEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public MEntry() {
        }

        public MEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }
    }

}
