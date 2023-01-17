package com.xm.designpattern.create.factory.abstractmethod;

import com.xm.designpattern.create.factory.entiry.Car;
import com.xm.designpattern.create.factory.entiry.Chair;

/**
 * 抽象工厂方法, 是一个工厂族
 * 是工厂方法模式和静态工厂模式的合并
 *
 * @author hongwan
 * @date 2022/12/14
 */
public interface ICarFactory {

    Car getCar();

    Chair getChair();
}
