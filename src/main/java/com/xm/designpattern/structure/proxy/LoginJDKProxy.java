package com.xm.designpattern.structure.proxy;

import com.xm.designpattern.structure.proxy.entity.Login;
import com.xm.designpattern.structure.proxy.entity.LoginImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 利用JDK的API,动态的在内存中构建代理对象
 * 采用拦截器(拦截器必须实现InvocationHanlder)加上反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
 *
 * @author hongwan
 * @date 2022/12/16
 */
public class LoginJDKProxy implements InvocationHandler {

    private Object target;

    public Object getInstance(final Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 当用户调用对象中的每个方法时都通过下面的方法执行
     *
     * @param proxy  被代理后的对象
     * @param method 将要被执行的方法信息（反射）
     * @param args   执行方法时需要的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---登录前校验---");
        Object result = method.invoke(target, args);
        System.out.println("---登录后动作---");
        return result;
    }

    public static void main(String[] args) {
        Login login = new LoginImpl();
        Login loginProxy = (Login) new LoginJDKProxy().getInstance(login);
        loginProxy.login();
    }
}

