package com.xm.designpattern.structure.adapter;

/**
 * 对象适配器模式
 * 可以适配一个适配者的子类，由于适配器和适配者之间是关联关系，适配者的子类也可以通过该适配器进行适配
 *
 * @author hongwan
 * @date 2023/1/4
 */
public class PowerAdapter2 implements PowerTarget {
    private PowerAdaptee powerAdaptee;

    public PowerAdapter2(PowerAdaptee powerAdaptee) {
        super();
        this.powerAdaptee = powerAdaptee;
    }

    @Override
    public int output5V() {
        int output = powerAdaptee.output220V();
        System.out.println("电源适配器开始工作，此时输出电压是：" + output);
        output = output / 44;
        System.out.println("电源适配器工作完成，此时输出电压是：" + output);
        return output;
    }
}
