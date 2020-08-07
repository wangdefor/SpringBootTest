package org.example.annotation;

import java.lang.annotation.*;

/**
 * @ClassName DataDynamicsAnnotation
 * @Description 简单的动态配置，改配置主要是用在Controller上
 * @Date 2020/6/18 14:07
 * @Author wangyong
 * @Version 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DataDynamicsAnnotations {

    String token() default "#token";

    String tenlanCode() default "#tenlanCode";

}
