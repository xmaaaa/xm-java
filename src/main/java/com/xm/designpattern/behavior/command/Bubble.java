package com.xm.designpattern.behavior.command;

/**
 * 具体被调用类
 *
 * @author XM
 * @date 2023/1/11
 */
public class Bubble {

    public void on() {
        System.out.println("bubble on");
    }

    public void off() {
        System.out.println("bubble off");
    }
}