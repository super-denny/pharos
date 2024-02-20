package com.pharos.common.annotation;

import java.lang.annotation.*;

/**
 * @author wcj
 * @ClassName Login
 * @Description 登录注解，需要登录的接口上需要加此注解
 * @createTime 2021-09-12
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoLogin {

}
