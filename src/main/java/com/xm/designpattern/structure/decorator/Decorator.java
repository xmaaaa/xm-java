package com.xm.designpattern.structure.decorator;

/**
 * 装饰者基类，持有一个将要被装饰的接口对象的实例
 *
 * @author hongwan
 * @date 2022/12/16
 */
class Decorator implements People {

    private final People people;

    public Decorator(People people) {
        this.people = people;
    }

    @Override
    public void wear() {
        people.wear();
    }
}
