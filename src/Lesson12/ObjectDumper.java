package Lesson12;

import java.lang.reflect.*;
import java.util.*;

public class ObjectDumper {
    private static final String INDENT = "  ";

    public static String dump(Object obj) {
        return dump(obj, 0, new IdentityHashMap<>());
    }

    private static String dump(Object obj, int level, Map<Object,Boolean> visited) {
        if (obj == null) {
            return indent(level) + "null\n";
        }
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();

        // Evita ciclos
        if (visited.put(obj, true) != null) {
            return indent(level) + "[circular ref to " + cls.getSimpleName() + "]\n";
        }

        sb.append(indent(level))
                .append(cls.getSimpleName())
                .append(" {\n");

        // percorre só campos declarados nesta classe
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            boolean isStatic = Modifier.isStatic(field.getModifiers());
            String marker = isStatic ? " static" : "";
            sb.append(indent(level + 1))
                    .append(field.getType().getSimpleName())
                    .append(marker)
                    .append(" ")
                    .append(field.getName())
                    .append(" = ");
            try {
                Object value = field.get(obj);
                if (value == null
                        || isPrimitiveOrWrapper(field.getType())
                        || field.getType() == String.class) {
                    sb.append(String.valueOf(value)).append("\n");
                } else if (field.getType().getPackage() != null
                        && (field.getType().getPackage().getName().startsWith("java.")
                        || field.getType().getPackage().getName().startsWith("javax."))) {
                    sb.append(value.getClass().getSimpleName())
                            .append(" instance\n");
                } else {
                    // recursão
                    sb.append("\n")
                            .append(dump(value, level + 2, visited));
                }
            } catch (IllegalAccessException e) {
                sb.append("[unable to access]\n");
            }
        }

        sb.append(indent(level)).append("}\n");
        return sb.toString();
    }

    private static boolean isPrimitiveOrWrapper(Class<?> type) {
        return type.isPrimitive() ||
                type == Integer.class ||
                type == Long.class    ||
                type == Boolean.class ||
                type == Byte.class    ||
                type == Character.class||
                type == Short.class   ||
                type == Double.class  ||
                type == Float.class;
    }

    private static String indent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) sb.append(INDENT);
        return sb.toString();
    }
}
