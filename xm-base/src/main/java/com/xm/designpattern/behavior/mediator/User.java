package com.xm.designpattern.behavior.mediator;

/**
 * 具体同事类
 *
 * @author XM
 * @date 2023/1/12
 */
class User extends WechatUser {

    public User(String name) {
        super(name);
    }

    @Override
    void receive() {
        // 中介者调用
        System.out.println(name + "收到了消息");
    }

    @Override
    void send() {
        System.out.println(name + "发送了消息");
        // 通过中介者发送
        mediator.send(this);
    }
}