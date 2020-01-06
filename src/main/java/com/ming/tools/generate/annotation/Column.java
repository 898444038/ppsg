package com.ming.tools.generate.annotation;

import com.ming.tools.generate.enums.ColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2020/1/6 0006.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    ColumnType type() default ColumnType.AUTO;//列类型
    String name() default "";//列名
    int length() default 0;//长度
    int pointLength() default 0;//小数点

}
