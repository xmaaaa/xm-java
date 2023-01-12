package com.xm.designpattern.behavior.interpreter;

/**
 * 抽象表达式
 *
 * @author XM
 * @date 2023/1/12
 */
public abstract class AbstractExpression {

    /**
     * 通过上下文解释表达式
     *
     * @param context
     * @return
     */
    public abstract int interpret(Context context);
}