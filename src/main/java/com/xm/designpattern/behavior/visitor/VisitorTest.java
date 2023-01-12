package com.xm.designpattern.behavior.visitor;

/**
 * 访问者模式
 * 我们使用了一个访问者类，它改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。它将对数据的操作与数据结构进行分离，是行为类模式中最复杂的一种模式。
 * 优点： 1、符合单一职责原则。 2、优秀的扩展性。 3、灵活性。
 * 缺点： 1、具体元素对访问者公布细节，违反了迪米特原则。 2、具体元素变更比较困难。 3、违反了依赖倒置原则，依赖了具体类，没有依赖抽象。
 *
 * @author hongwan
 * @date 2023/1/12
 */
public class VisitorTest {

    public static void main(String[] args) {
        // 创建房子
        Home home = new Home();
        // 添加房间
        home.addRoom(new RoomA());
        home.addRoom(new RoomB());

        // 张三
        ZhangSan zhangsan = new ZhangSan();
        // 张三访问房间
        home.accept(zhangsan);

        // 李四
        LiSi lisi = new LiSi();
        home.accept(lisi);
    }
}
