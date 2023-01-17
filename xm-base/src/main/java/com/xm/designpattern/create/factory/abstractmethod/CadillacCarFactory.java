package com.xm.designpattern.create.factory.abstractmethod;

import com.xm.designpattern.create.factory.entiry.Cadillac;
import com.xm.designpattern.create.factory.entiry.CadillacChair;
import com.xm.designpattern.create.factory.entiry.Car;
import com.xm.designpattern.create.factory.entiry.Chair;

/**
 * @author hongwan
 * @date 2022/12/14
 */
public class CadillacCarFactory implements ICarFactory {

    @Override
    public Car getCar() {
        return new Cadillac();
    }

    @Override
    public Chair getChair() {
        return new CadillacChair();
    }
}
