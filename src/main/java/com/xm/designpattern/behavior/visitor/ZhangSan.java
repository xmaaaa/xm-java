package com.xm.designpattern.behavior.visitor;

/**
 * 访问者
 *
 * @author XM
 * @date 2023/1/12
 */
public class ZhangSan implements Friend {

    @Override
    public void visit(RoomA roomA) {
        System.out.println("张三访问房间A");
    }

    @Override
    public void visit(RoomB roomB) {
        System.out.println("张三访问房间B");
    }
}