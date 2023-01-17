package com.xm.designpattern.behavior.state;

/**
 * 抽象状态类
 *
 * @author XM
 * @date 2023/1/12
 */
public abstract class State {

    /**
     * 状态名
     */
    protected String stateName;

    /**
     * 可以通过行为来更新状态, 可以是任何行为
     *
     * @param context
     */
    public abstract void doAction(Context context);
}