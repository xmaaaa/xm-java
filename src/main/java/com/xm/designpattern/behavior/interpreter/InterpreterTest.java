package com.xm.designpattern.behavior.interpreter;

/**
 * 解释器模式（Interpreter Pattern）提供了评估语言的语法或表达式的方式
 * 应用实例：编译器、运算表达式计算。
 * 优点: 1、可扩展性比较好 2、增加了新的解释表达式的方式。 3、易于实现简单文法。
 * 缺点: 1、可利用场景比较少。 2、对于复杂的文法比较难维护。 3、解释器模式会引起类膨胀。 4、解释器模式采用递归调用方法。
 *
 * @author hongwan
 * @date 2023/1/12
 */
public class InterpreterTest {

    public static void main(String[] args) {
        // 定义环境类
        Context context = new Context();

        // 定义变量
        VariableExpression a = new VariableExpression("a");
        VariableExpression b = new VariableExpression("b");
        VariableExpression c = new VariableExpression("c");

        // 将变量添加到环境类
        context.addVar(a, 1);
        context.addVar(b, 2);
        context.addVar(c, 3);

        // 运算   a+b-3  即1+2-3
        AbstractExpression expression = new PlusExpression(a, new MinusExpression(b, c));
        // 结果
        int res = expression.interpret(context);
        System.out.println("结果为：" + res);
    }
}
