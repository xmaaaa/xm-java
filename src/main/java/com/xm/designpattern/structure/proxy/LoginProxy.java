package com.xm.designpattern.structure.proxy;

import com.xm.designpattern.structure.proxy.entity.Login;

/**
 * 静态代理类
 * 和装饰器区别:
 * 为让自己的能力增强，使得增强后的自己能够使用更多的方法，拓展在自己基础之上的功能的，叫装饰器模式
 * 让别人帮助你做你并不关心的事情，叫代理模式
 *
 * @author hongwan
 * @date 2022/12/16
 */
public class LoginProxy implements Login {

    /**
     * 接收保存目标对象
     */
    private final Login target;

    public LoginProxy(Login target) {
        this.target = target;
    }

    @Override
    public void login() {
        System.out.println("---开始校验---");
        // 执行目标对象的方法
        target.login();
        System.out.println("---成功上线---");
    }
}
