package com.xm.designpattern.create.factory.abstractmethod;

import com.xm.designpattern.create.factory.entiry.Car;
import com.xm.designpattern.create.factory.entiry.Chair;
import com.xm.designpattern.create.factory.entiry.Tesla;
import com.xm.designpattern.create.factory.entiry.TeslaChair;

/**
 * @author hongwan
 * @date 2022/12/14
 */
public class TeslaCarFactory implements ICarFactory {
    @Override
    public Car getCar() {
        return new Tesla();
    }

    @Override
    public Chair getChair() {
        return new TeslaChair();
    }
}
