package com.github.microwww.dylog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String joinFields(Class<?> clazz, Class<?> field) {
        return fields(clazz, field).stream().map(Field::getName).collect(Collectors.joining(","));
    }

    public static <T,U> List<Field> fields(Class<T> clazz, Class<U> field) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        for (Field f : fields) {
            if (f.getType().equals(field)) {
                list.add(f);
            }
        }
        return list;
    }
}
