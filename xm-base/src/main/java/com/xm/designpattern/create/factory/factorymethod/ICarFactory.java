package com.xm.designpattern.create.factory.factorymethod;

import com.xm.designpattern.create.factory.entiry.Car;

/**
 * 工厂方法模式
 * 符合开闭原则，增加⼀个产品类，只需要实现其他具体的产品类和具体的⼯⼚类
 * 每个工厂只创建⼀类具体类的对象， 后续发展可能会导致工厂类过多
 *
 * @author hongwan
 * @date 2022/12/14
 */
public interface ICarFactory {
    /**
     * 获取car
     *
     * @return
     */
    Car getCar();
}
