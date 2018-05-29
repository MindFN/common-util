package com.util.collection;

import com.google.common.base.Function;
import com.util.collection.Tree.TreeNode;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * 生成树工具
 *
 * @author wulang
 * @version v1.0
 * @date 2017年11月24日 15:56
 * @description
 * @modified By:
 * @modifued reason:
 */
public class TreeNodeUtil {
    /**
     * 通过计算容器中各个元素的当前的唯一标识，及父节点的唯一标识，将容器转换成树状结构
     *
     * @param collection
     * @param entryFunction
     * @param parentKeyFunction
     * @return
     * @author: wulang
     * @date: 2017/11/24 16:48
     * @modify by user: {修改人}  2017/11/24 16:48
     * @modify by reason:
     */
    public static <F, K, V extends TreeNode<F>, T extends Map.Entry<K, V>> TreeNode<F> generateTree(Collection<F> collection, Function<F, T> entryFunction, Function<F, K> parentKeyFunction) {
        TreeNode<F> rootTreeNode = new TreeNode<>();
        TreeNode<F> parentTreeNode = null;
        TreeNode<F> currentTreeNode = null;
        if (CollectionUtils.isEmpty(collection) && null == entryFunction) {
            return rootTreeNode;
        }
        K uniqueParentKey = null;
        Map<K, V> collectionMap = Collection7Utils.transformCollectionToMap(collection, entryFunction);
        Map.Entry<K, V> entry = null;
        Iterator<Map.Entry<K, V>> iterator = collectionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            entry = iterator.next();
            currentTreeNode = entry.getValue();
            uniqueParentKey = parentKeyFunction.apply(currentTreeNode.getNode());
            parentTreeNode = collectionMap.get(uniqueParentKey);
            if (null == parentTreeNode) {
                parentTreeNode = rootTreeNode;
            }
            parentTreeNode.appendChild(currentTreeNode);
        }
        return rootTreeNode;
    }
}
