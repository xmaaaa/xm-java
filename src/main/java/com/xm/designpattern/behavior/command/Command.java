package com.xm.designpattern.behavior.command;

/**
 * 命令接口, 该类与调用者绑定, 调用者无需关心具体实现
 *
 * @author XM
 * @date 2023/1/11
 */
public interface Command {

    /**
     * 命令执行
     */
    void execute();

    /**
     * 命令撤销
     */
    void undo();
}