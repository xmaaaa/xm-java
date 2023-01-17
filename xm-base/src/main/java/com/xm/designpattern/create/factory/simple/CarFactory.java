package com.xm.designpattern.create.factory.simple;

import com.xm.designpattern.create.factory.entiry.Cadillac;
import com.xm.designpattern.create.factory.entiry.Car;
import com.xm.designpattern.create.factory.entiry.Tesla;

/**
 * 简单工厂模式
 * 优点: 实现简单
 * 缺点: 改动需要改动代码, 不满足开闭原则
 *
 * @author hongwan
 * @date 2022/12/14
 */
public class CarFactory {

    /**
     * 根据请求参数，返回指定对象！
     *
     * @param name
     * @return
     */
    public static Car getCar(String name) {
        if ("Cadillac".equals(name)) {
            return new Cadillac();
        } else if ("Tesla".equals(name)) {
            return new Tesla();
        } else {
            return null;
        }
    }
}
