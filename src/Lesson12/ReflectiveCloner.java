package Lesson12;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectiveCloner {

    @SuppressWarnings("unchecked")
    public static <T> T cloneShallow(T src) {
        if (src == null) {
            return null;
        }
        try {
            Class<?> cls = src.getClass();
            // 1) pega o construtor sem-args
            Constructor<?> ctor = cls.getDeclaredConstructor();
            ctor.setAccessible(true);
            // 2) instancia o clone
            Object clone = ctor.newInstance();

            // 3) copia campos declarados
            for (Field field : cls.getDeclaredFields()) {
                field.setAccessible(true);
                // ignora static
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Object value = field.get(src);
                field.set(clone, value);
            }
            return (T) clone;

        } catch (Exception e) {
            throw new RuntimeException("Falha ao clonar via reflexão", e);
        }
    }
}
