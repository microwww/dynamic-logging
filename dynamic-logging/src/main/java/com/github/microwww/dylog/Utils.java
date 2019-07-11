package com.github.microwww.dylog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String joinFields(Class<?> clazz, Class<?> field) {
        List<Field> list = fields(clazz, field);
        StringBuilder buf = new StringBuilder();
        for (Field f : list) {
            buf.append(f.getName()).append(",");
        }
        return buf.toString();
    }

    public static <T, U> List<Field> fields(Class<T> clazz, Class<U> field) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<Field>();
        for (Field f : fields) {
            if (f.getType().equals(field)) {
                list.add(f);
            }
        }
        return list;
    }

    public static void tryMultiCatches(Exception e, Class<?>... catchs) {
        for (Class ex : catchs) {
            if (ex.isInstance(e)) {
                return;
            }
        }
        throw new RuntimeException(e);
    }
}
