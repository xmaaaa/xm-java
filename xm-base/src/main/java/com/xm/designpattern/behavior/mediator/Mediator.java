package com.xm.designpattern.behavior.mediator;

/**
 * 抽象中介者
 *
 * @author XM
 * @date 2023/1/12
 */
abstract class Mediator {

    /**
     * 注册同事类
     *
     * @param wechatUser
     */
    public abstract void register(WechatUser wechatUser);

    /**
     * 转发消息
     *
     * @param wechatUser
     */
    public abstract void send(WechatUser wechatUser);
}