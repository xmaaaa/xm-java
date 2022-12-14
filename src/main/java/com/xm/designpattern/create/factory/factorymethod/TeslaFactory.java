package com.xm.designpattern.create.factory.factorymethod;

import com.xm.designpattern.create.factory.entiry.Car;
import com.xm.designpattern.create.factory.entiry.Tesla;

/**
 * @author hongwan
 * @date 2022/12/14
 */
public class TeslaFactory implements ICarFactory {

    @Override
    public Car getCar() {
        return new Tesla();
    }
}
