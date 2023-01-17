package com.xm.designpattern.structure.facade;

/**
 * 外观模式
 * 1. 外观模式对外屏蔽了子系统的细节，因此外观模式降低了客户端对子系统使用的复杂性
 * 2. 外观模式对客户端与子系统的耦合关系进行解耦，让子系统内部的模块更易维护和扩展
 * 3. 通过合理的使用外观模式，可以帮我们更好的划分访问的层次
 *
 * @author hongwan
 * @date 2023/1/5
 */
public class FacadeTest {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }
}
