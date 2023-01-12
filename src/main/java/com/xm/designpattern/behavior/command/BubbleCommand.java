package com.xm.designpattern.behavior.command;

/**
 * 命令实现类，可以自由替换
 *
 * @author XM
 * @date 2023/1/11
 */
public class BubbleCommand implements Command {

    private final Bubble bubble;

    public BubbleCommand(Bubble bubble) {
        this.bubble = bubble;
    }

    @Override
    public void execute() {
        bubble.on();
    }

    @Override
    public void undo() {
        bubble.off();
    }
}