package com.xm.designpattern.structure.proxy;

import com.xm.designpattern.structure.proxy.entity.Login;
import com.xm.designpattern.structure.proxy.entity.LoginImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理: 生成代理是被代理对象的子类。因此它拥有继承方法实现静态代理的优点：不需要被代理对象实现某个接口
 *
 * @author hongwan
 * @date 2022/12/16
 */
public class LoginCglib implements MethodInterceptor {

    /**
     * 相当于JDK动态代理中的绑定
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        // 创建加强器，用来创建动态代理类
        enhancer.setSuperclass(target.getClass());
        // 为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    /**
     * 实现回调方法
     *
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("---登录前校验---");
        // 调用业务类（父类中）的方法
        proxy.invokeSuper(obj, args);
        System.out.println("---登录后动作---");
        return null;
    }

    public static void main(String[] args) {
        Login login = new LoginImpl();
        Login loginProxy = (Login) new LoginCglib().getInstance(login);
        loginProxy.login();
    }
}
