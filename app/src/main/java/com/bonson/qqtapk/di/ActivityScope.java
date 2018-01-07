package com.bonson.qqtapk.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zjw on 2017/12/29.
 *
 * Identifies a type that the injector only instantiates once. Not inherited.
 * @see javax.inject.Scope @Scope
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {
}
