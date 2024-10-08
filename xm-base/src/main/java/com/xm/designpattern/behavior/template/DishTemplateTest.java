package com.xm.designpattern.behavior.template;

/**
 * 模板模式
 * 抽象父类定义方法模板，子类自定义实现方式。
 * 优点： 1、封装不变部分，扩展可变部分。 2、提取公共代码，便于维护。 3、行为由父类控制，子类实现。
 * 缺点：每一个不同的实现都需要一个子类来实现，导致类的个数增加，使得系统更加庞大。
 * 使用场景： 1、有多个子类共有的方法，且逻辑相同。 2、重要的、复杂的方法，可以考虑作为模板方法。
 * 注意事项：为防止恶意操作，一般模板方法都加上 final 关键词。
 * <p>
 * 使用场景很多，只要有相同的执行逻辑，或者重要的复杂的方法，都可以交给父类管理。
 * 比如开发XX系统步骤都是一样的，立项，需求，开发，测试，上线。
 *
 * @author XM
 * @since 2023/1/11
 */
public class DishTemplateTest {

    public static void main(String[] args) {
        System.out.println("----------西红柿炒鸡蛋----------");
        DishTemplate eggsWithTomato = new DishEggsWithTomato();
        eggsWithTomato.doDish();

        System.out.println("----------小葱拌豆腐----------");
        DishTemplate onionWithTofu = new DishOnionWithTofu();
        onionWithTofu.doDish();
    }
}