package com.util.math;

import com.util.common.ObjectUtil;
import com.util.math.sort.SortMethod;
import com.util.math.sort.impl.BuddleSort;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wulang
 * @Date: 2017年10月13日 14:09
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class SortMethodFactory {

    static Map<SortMethodEnum, SortMethod> methodMap = new HashMap<>();


    public static SortMethod getSortMethod(SortMethodEnum methodType) {
        SortMethod sortMethod = methodMap.get(methodType);
        if (ObjectUtil.isNull(sortMethod)) {
            sortMethod = createSortMethod(methodType);
            methodMap.put(methodType, sortMethod);
        }
        return sortMethod;
    }

    private static SortMethod createSortMethod(SortMethodEnum methodType) {
        SortMethod method = null;
        switch (methodType) {
            case HEAP_SORT:
                break;
            case INSET_SORT:
                break;
            case MERGE_SORT:
                break;
            case QUICK_SORT:
                break;
            case RADIX_SORT:
                break;
            case SHELL_SORT:
                break;
            case BUBBLE_SORT:
                method = BuddleSort.getInstance();
                break;
            case SELECTION_SORT:
                break;
            default:
                break;
        }
        return method;
    }

}
