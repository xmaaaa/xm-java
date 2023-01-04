package com.xm.designpattern.structure.adapter;

/**
 * 类适配器
 * 不支持多重类继承的语言，一次最多只能适配一个适配者类，不同同时适配多个适配者
 *
 * @author XM
 * @date 2023/1/4
 */
public class PowerAdapter extends PowerAdaptee implements PowerTarget {

    @Override
    public int output5V() {
        int output = output220V();
        System.out.println("电源适配器开始工作，此时输出电压是：" + output);
        output = output / 44;
        System.out.println("电源适配器工作完成，此时输出电压是：" + output);
        return output;
    }

}