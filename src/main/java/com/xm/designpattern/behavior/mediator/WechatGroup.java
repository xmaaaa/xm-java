package com.xm.designpattern.behavior.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 *
 * @author XM
 * @date 2023/1/12
 */
class WechatGroup extends Mediator {

    /**
     * 存储同事类
     */
    private final List<WechatUser> list = new ArrayList<>();

    @Override
    public void register(WechatUser wechatUser) {
        list.add(wechatUser);
    }

    @Override
    public void send(WechatUser wechatUser) {
        for (WechatUser user : list) {
            if (!user.equals(wechatUser)) {
                user.receive();
            }
        }
    }
}