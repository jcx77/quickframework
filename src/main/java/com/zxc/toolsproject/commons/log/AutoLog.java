package com.zxc.toolsproject.commons.log;

import com.zxc.toolsproject.commons.utils.JsonUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLog {
    String value();

    LogType type() default LogType.ACTION;
}
