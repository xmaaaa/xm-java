package com.xm.designpattern.behavior.state;

/**
 * 状态模式，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象
 * 使用场景：
 * 当一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为时，就可以考虑使用状态模式。
 *
 * @author hongwan
 * @date 2023/1/12
 */
public class StateTest {

    public static void main(String[] args) {
        Context context = new Context();
        context.setScore(59);
        System.out.println("当前状态：" + context.getState().stateName);

        context.setScore(99);
        System.out.println("当前状态：" + context.getState().stateName);
    }
}
