package com.xm.designpattern.create.factory.entiry;

/**
 * @author hongwan
 * @date 2022/12/14
 */
public class TeslaChair implements Chair {

    @Override
    public void name() {
        System.out.println("Tesla Chair");
    }
}
