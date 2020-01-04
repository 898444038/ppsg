package com.ming.system.annotation.methodtype;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/12/9 0009.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Upload {

}
