package com.xm.designpattern.behavior.state;

/**
 * 上下文环境角色, 用于维护state实例, 并且定义行为
 *
 * @author XM
 * @date 2023/1/12
 */
public class Context {

    private int score;

    private State state;

    Context() {
        state = new LowState();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setScore(int score) {
        this.score = score;
        state.doAction(this);
    }

    public int getScore() {
        return score;
    }
}