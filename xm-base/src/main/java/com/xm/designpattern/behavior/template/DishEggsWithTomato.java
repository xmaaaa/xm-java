package com.xm.designpattern.behavior.template;

/**
 * 西红柿炒鸡蛋
 *
 * @author xm
 * @since 2023/1/11
 */
public class DishEggsWithTomato extends DishTemplate {

    @Override
    public void preparation() {
        System.out.println("准备西红柿鸡蛋...");
    }

    @Override
    public void doing() {
        System.out.println("开始炒西红柿鸡蛋...");
    }

    @Override
    public void carriedDishes() {
        System.out.println("西红柿鸡蛋装盘...");
    }
}