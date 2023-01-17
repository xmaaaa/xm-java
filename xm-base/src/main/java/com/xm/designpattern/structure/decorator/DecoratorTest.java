package com.xm.designpattern.structure.decorator;

/**
 * 装饰模式可以提供比继承更多的灵活性。装饰模式允许动态增加或删除一个装饰的功能。继承需要在此之前就要确定好对应的类。
 * 像例子里面一个人穿什么衣服，按照什么顺序来穿都是不定的，如果使用继承要考虑这么多情况就需要很多的类，然而使用装饰者就可以很方便的在使用的时候动态的创造组合不同的行为。
 *
 * @author hongwan
 * @date 2022/12/16
 */
public class DecoratorTest {

    public static void main(String[] args) {
        People xm = new XM();
        // 夏天
        xm = new DecoratorShoes(new DecoratorPants(new DecoratorShirt(xm)));
        xm.wear();
        System.out.println("--------------");
        People xm2 = new XM();
        // 秋天穿两件
        xm2 = new DecoratorShoes(new DecoratorPants(new DecoratorShirt(new DecoratorShirt(xm2))));
        xm2.wear();
    }
}
