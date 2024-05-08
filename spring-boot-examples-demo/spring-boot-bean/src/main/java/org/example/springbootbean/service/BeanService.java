package org.example.springbootbean.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor,
        InitializingBean, DisposableBean {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        super.
        System.out.println("BeanService setBeanFactory");
//        beanFactory.
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanService setBeanName: " + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanService destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanService afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("BeanService setApplicationContext");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanService")) {
            System.out.println("BeanService find postProcessBeforeInitialization beanName: " + beanName);
        }
        System.out.println("BeanService postProcessBeforeInitialization beanName: " + beanName);


        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("beanService")) {
            System.out.println("BeanService find postProcessAfterInitialization beanName: " + beanName);
        }
        System.out.println("BeanService postProcessAfterInitialization beanName: " + beanName);

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    public String getResponse() {
        return "test bean life cycle";
    }
}
