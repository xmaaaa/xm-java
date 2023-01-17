package com.xm.designpattern.create.factory.factorymethod;

import com.xm.designpattern.create.factory.entiry.Cadillac;
import com.xm.designpattern.create.factory.entiry.Car;

/**
 * @author hongwan
 * @date 2022/12/14
 */
public class CadillacFactory implements ICarFactory {

    @Override
    public Car getCar() {
        return new Cadillac();
    }
}
