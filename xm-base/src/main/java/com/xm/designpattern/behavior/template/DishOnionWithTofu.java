package com.xm.designpattern.behavior.template;

/**
 * 小葱拌豆腐
 *
 * @author xm
 * @since 2023/1/11
 */
public class DishOnionWithTofu extends DishTemplate {

    @Override
    public void preparation() {
        System.out.println("准备小葱豆腐...");
    }

    @Override
    public void doing() {
        System.out.println("开始炒小葱拌豆腐...");
    }

    @Override
    public void carriedDishes() {
        System.out.println("小葱拌豆腐装盘...");
    }
}