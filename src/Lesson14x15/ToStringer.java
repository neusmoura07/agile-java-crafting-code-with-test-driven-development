package Lesson14x15;

import java.lang.reflect.*;
import java.util.*;

public class ToStringer {

    public static String toString(Object obj) {
        if (obj == null) return "null";

        StringBuilder sb = new StringBuilder();
        Class<?> clazz = obj.getClass();
        sb.append(clazz.getSimpleName()).append(" [");

        Field[] fields = clazz.getDeclaredFields();
        List<Field> dumpFields = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Dump.class)) {
                dumpFields.add(field);
            }
        }

        dumpFields.sort(Comparator.comparingInt(f -> f.getAnnotation(Dump.class).order()));

        boolean first = true;
        for (Field field : dumpFields) {
            field.setAccessible(true);
            if (!first) sb.append(", ");
            try {
                Object value = field.get(obj);
                Dump annotation = field.getAnnotation(Dump.class);
                String[] methodNames = annotation.outputMethods();
                String valueStr;

                if (value == null) {
                    valueStr = "null";
                } else {
                    List<String> results = new ArrayList<>();
                    for (String methodName : methodNames) {
                        try {
                            Method m = value.getClass().getMethod(methodName);
                            Object result = m.invoke(value);
                            results.add(String.valueOf(result));
                        } catch (NoSuchMethodException e) {
                            results.add("<método não encontrado>");
                        } catch (Exception e) {
                            results.add("<erro>");
                        }
                    }
                    valueStr = String.join(" ", results);
                }

                if (annotation.quote()) {
                    valueStr = "\"" + valueStr + "\"";
                }

                sb.append(field.getName()).append("=").append(valueStr);
            } catch (IllegalAccessException e) {
                sb.append(field.getName()).append("=<inacessível>");
            }
            first = false;
        }

        sb.append("]");
        return sb.toString();
    }
}
