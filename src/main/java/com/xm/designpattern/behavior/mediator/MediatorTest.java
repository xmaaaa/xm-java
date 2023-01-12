package com.xm.designpattern.behavior.mediator;

/**
 * 中介者模式
 * 用来降低多个对象和类之间的通信复杂性。这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护。
 * 优点： 1、降低了类的复杂度，将一对多转化成了一对一。 2、各个类之间的解耦。 3、符合迪米特原则。
 * 缺点: 中介者会庞大，变得复杂难以维护。
 *
 * @author hongwan
 * @date 2023/1/12
 */
public class MediatorTest {

    public static void main(String[] args) {
        // 群聊也就是中介者
        WechatGroup wechatGroup = new WechatGroup();

        // 创建用户
        User zhangsan = new User("张三");
        User lisi = new User("李四");
        User laowu = new User("老五");

        // 用户加入群聊（中介者）
        wechatGroup.register(zhangsan);
        wechatGroup.register(lisi);
        wechatGroup.register(laowu);

        // 用户持有中介者
        zhangsan.setMediator(wechatGroup);
        lisi.setMediator(wechatGroup);
        laowu.setMediator(wechatGroup);

        zhangsan.send();
        System.out.println("-----");
        lisi.send();
    }
}
