package com.ming.tools.generate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateMybatis {
    String tableName() default "";
    String engine() default "InnoDB";
    String charset() default "utf8";
}
