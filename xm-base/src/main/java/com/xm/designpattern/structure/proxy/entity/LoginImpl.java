package com.xm.designpattern.structure.proxy.entity;

/**
 * @author hongwan
 * @date 2022/12/16
 */
public class LoginImpl implements Login {

    @Override
    public void login() {
        System.out.println("正在上线~~~~");
    }
}
