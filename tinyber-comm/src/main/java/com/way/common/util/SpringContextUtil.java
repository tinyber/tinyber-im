package com.tiny.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 *  非@import显式注入，@Component是必须的，且该类必须与main同包或子包
	 *  若非同包或子包，则需手动import 注入，有没有@Component都一样
	 *  可复制到Test同包测试
	 * @param applicationContext 应用程序上下文
	 * @throws BeansException BeansException
	 */
	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static void setApplicationContextByMain(ApplicationContext applicationContext) {
		if (SpringContextUtil.applicationContext == null) {
			SpringContextUtil.applicationContext = applicationContext;
		}
	}

	/**
	 * 获取对象
	 *
	 * @param name bean的名称
	 * @param <T>  类型
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException BeansException
	 */
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 *
	 * @param clz bean的类型
	 * @param <T> 类型
	 * @return 对象实例
	 * @throws BeansException BeansException
	 */
	public static <T> T getBean(Class<T> clz) throws BeansException {
		return applicationContext.getBean(clz);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name 类的名称
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name 对象的名称
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}

	/**
	 * @param name 类的名称
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 *
	 * @param name 对象的名称
	 * @return 对象的别名
	 * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> cls) {
		return applicationContext.getBeansOfType(cls);
	}

	/**
	 * 获取当前环境
	 *
	 * @return current environment
	 */
	public static String getActiveProfile() {
		String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
		return profiles.length == 0 ? "" : profiles[0];
	}

}
