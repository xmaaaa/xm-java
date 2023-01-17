package com.xm.designpattern.behavior.interpreter;

import lombok.EqualsAndHashCode;

/**
 * 变量表达式, 这是最底层的解释器
 *
 * @author XM
 * @date 2023/1/12
 */
@EqualsAndHashCode(callSuper = true)
public class VariableExpression extends AbstractExpression {
    /**
     * 声明存储变量名
     */
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Context context) {
        return context.getValue(this);
    }
}