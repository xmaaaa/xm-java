package com.xm.designpattern.structure.facade;

/**
 * 外观角色，客户端通过操作外观角色从而达到控制子系统角色的目的。对于客户端来说，外观角色好比一道屏障，对客户端屏蔽了子系统的具体实现。
 *
 * @author XM
 * @date 2023/1/5
 */
public class Computer {
    
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        // 子系统角色。表示一个系统的子系统或模块。
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {
        System.out.println("start the computer!");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("start computer finished!");
    }

    public void shutdown() {
        System.out.println("begin to close the computer!");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer closed!");
    }
}