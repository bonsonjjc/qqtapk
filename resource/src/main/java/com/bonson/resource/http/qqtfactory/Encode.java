package com.bonson.resource.http.qqtfactory;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zjw on 2018/1/2.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Encode {
    boolean encoder() default false;

    boolean decoder() default false;
}