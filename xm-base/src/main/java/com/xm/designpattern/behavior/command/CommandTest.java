package com.xm.designpattern.behavior.command;

/**
 * 命令模式
 * <p>
 * 优点
 * 1、请求发送者和接收者完全解耦，发送者与接收者之间没有直接引用关系，降低了系统耦合度。
 * 2、新的命令可以很容易添加到系统中去。由于增加新的具体命令类不会影响到其他类，因此增加新的具体命令类很容易，无须修改原有系统源代码，甚至客户类代码，满足“开闭原则”的要求。
 * 3、可以比较容易地设计一个命令队列或宏命令（组合命令）。
 * 4、为请求的撤销(Undo)和恢复(Redo)操作提供了一种设计和实现方案。
 *
 * @author XM
 * @date 2023/1/11
 */
public class CommandTest {

    public static void main(String[] args) {

        // 一个命令实现, 如果需要替换需要改动此处
        BubbleCommand command = new BubbleCommand(new Bubble());

        RemoteControl remoteControl = new RemoteControl(command);
        remoteControl.action();
        remoteControl.undo();
    }
}