package com.xm.designpattern.structure.adapter;


/**
 * 目标角色（target）：这是客户锁期待的接口。目标可以是具体的或抽象的类，也可以是接口
 *
 * @author XM
 * @date 2023/1/4
 */
public interface PowerTarget {

    /**
     * 输出5v的电流
     */
    int output5V();
}