package com.xm.designpattern.structure.decorator;

/**
 * 具体的装饰者类，负责给增加附加的操作：穿裤子
 *
 * @author hongwan
 * @date 2022/12/16
 */
class DecoratorPants extends Decorator {

    public DecoratorPants(People people) {
        super(people);
    }

    @Override
    public void wear() {
        super.wear();
        System.out.println("穿上裤子");
    }
}
