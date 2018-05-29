package com.util.io.rule;

import com.google.common.base.Predicate;
import com.util.io.annotation.FileField;

import java.lang.reflect.Field;

/**
 * 字段过滤
 *
 * @author: wulang
 * @Date: 2017年10月09日 20:01
 * @Version: v1.0
 * @Description:
 * @Modified By:
 * @Modifued reason
 */
public class FieldPredicate implements Predicate<Field> {

    private static volatile FieldPredicate INSTANCE;

    private FieldPredicate() {
    }

    /**
     * 获取实例
     *
     * @return
     * @author: wulang
     * @date: 2017/12/1 11:32
     * @modify by user: {修改人}  2017/12/1 11:32
     * @modify by reason:
     */
    public static FieldPredicate getInstance() {
        if (null == INSTANCE) {
            synchronized (FieldPredicate.class) {
                if (null == INSTANCE) {
                    INSTANCE = new FieldPredicate();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public boolean apply(Field input) {
        return input.isAnnotationPresent(FileField.class);
    }
}
