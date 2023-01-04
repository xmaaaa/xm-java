package com.xm.designpattern.structure.adapter;


/**
 * 适配者角色（adaptee）：已有接口，但是和客户器期待的接口不兼容
 *
 * @author XM
 * @date 2023/1/4
 */
public class PowerAdaptee {

    private int output = 220;

    public int output220V() {
        System.out.println("电源输出电压：" + output);
        return output;
    }
}