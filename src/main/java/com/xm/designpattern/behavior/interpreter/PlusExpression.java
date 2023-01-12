package com.xm.designpattern.behavior.interpreter;

/**
 * 加法表达式
 *
 * @author XM
 * @date 2023/1/12
 */
public class PlusExpression extends AbstractExpression {

    private final AbstractExpression left;
    private final AbstractExpression right;

    public PlusExpression(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        // 返回左边的值 + 右边的值
        return left.interpret(context) + right.interpret(context);
    }
}