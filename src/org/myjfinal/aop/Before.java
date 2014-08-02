package org.myjfinal.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited // 允许自雷继承父类的注解
@Retention(RetentionPolicy.RUNTIME) // 注解信息被加载到JVM 
@Target({ElementType.TYPE, ElementType.METHOD}) // 定义注解作用的目标：接口、类、枚举、注解，方法。
public @interface Before {
	Class<? extends Interceptor>[] value();
}
