package com.util.math.sort;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

/**
 * 排序方法的抽象接口
 *
 * @Author: wulang
 * @Date: 2017年10月13日 14:15
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public interface SortMethod{

    /**
     * 容器排序
     *
     * @param collection
     * @param comparator
     * @return
     * @author: wulang
     * @date: 2017/10/13 14:21
     * @modify by user: {修改人}  2017/10/13 14:21
     * @modify by reason:
     */
    <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator);

    /**
     * Map使用Key排序
     *
     * @param map
     * @param comparator
     * @return
     * @author: wulang
     * @date: 2017/10/13 14:21
     * @modify by user: {修改人}  2017/10/13 14:21
     * @modify by reason:
     */
    <K, V> Map<K, V> sortWithKey(Map<K, V> map, Comparator<K> comparator);

    /**
     * Map使用Value排序
     *
     * @param map
     * @param comparator
     * @return
     * @author: wulang
     * @date: 2017/10/13 14:21
     * @modify by user: {修改人}  2017/10/13 14:21
     * @modify by reason:
     */
    <K, V> Map<K, V> sortWithValue(Map<K, V> map, Comparator<V> comparator);



}
