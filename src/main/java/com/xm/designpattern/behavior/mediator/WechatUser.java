package com.xm.designpattern.behavior.mediator;

/**
 * 抽象同事类
 *
 * @author XM
 * @date 2023/1/12
 */
abstract class WechatUser {
    /**
     * 持有中介者（群聊）
     */
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public String name;

    public WechatUser(String name) {
        this.name = name;
    }

    //接收
    abstract void receive();

    //发送
    abstract void send();
}