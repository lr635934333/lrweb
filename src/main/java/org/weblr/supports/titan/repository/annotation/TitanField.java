package org.weblr.supports.titan.repository.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/8/24.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TitanField {
    String name() default "";
}
