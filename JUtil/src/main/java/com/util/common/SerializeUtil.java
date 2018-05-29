package com.util.common;

import java.io.*;

/**
 *  序列化工具
 *
 * @author wulang
 * @version v1.0
 * @date 2017年11月24日 15:56
 * @description
 * @modified By:
 * @modifued reason:
 */
public class SerializeUtil {
    /**
     * 默认路径
     */
    private static String DEFAULT_DIR = System.getProperty("user.dir") + File.separator + "temp";
    /**
     * 默认后缀
     */
    private static String DEFAULT_SUFFIX = ".obj";

    /**
     * 序列化对象
     *
     * @param obj
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:34
     * @modify by user: {修改人}  2017/12/1 10:34
     * @modify by reason:
     */
    public static void writeObject(Serializable obj) {
        writeObject(DEFAULT_DIR + File.separator + obj.getClass().getName() + DEFAULT_SUFFIX, obj);
    }

    /**
     * 序列化对象到指定路径
     *
     * @param path
     * @param o
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:34
     * @modify by user: {修改人}  2017/12/1 10:34
     * @modify by reason:
     */
    public static void writeObject(String path, Serializable o) {
        if (null == o) {
            throw new NullPointerException("对象为null,不能写出!");
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化一个对象
     *
     * @param clazz
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:34
     * @modify by user: {修改人}  2017/12/1 10:34
     * @modify by reason:
     */
    public static <T> T readObject(Class<T> clazz) {
        return readObject(DEFAULT_DIR + File.separator + clazz.getName() + DEFAULT_SUFFIX, clazz);
    }

    /**
     * 反从指定路径序列化对象
     *
     * @param path
     * @param clazz
     * @return
     * @author: wulang
     * @date: 2017/12/1 10:33
     * @modify by user: {修改人}  2017/12/1 10:33
     * @modify by reason:
     */
    public static <T> T readObject(String path, Class<T> clazz) {
        File file = new File(path);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
