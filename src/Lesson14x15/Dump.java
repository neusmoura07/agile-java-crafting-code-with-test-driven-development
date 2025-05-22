package Lesson14x15;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dump {
    int order() default Integer.MAX_VALUE;
    boolean quote() default false;
    String[] outputMethods() default {"toString"};
}
