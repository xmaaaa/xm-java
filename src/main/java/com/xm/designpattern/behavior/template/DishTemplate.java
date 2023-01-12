package com.xm.designpattern.behavior.template;

/**
 * 做菜模板类
 * 用于定义做菜的执行步骤，所有菜都可以按照这种方式做菜
 *
 * @author xm
 * @since 2023/1/11
 */
public abstract class DishTemplate {
    /**
     * 做菜执行步骤
     */
    public final void doDish() {
        this.preparation();
        this.doing();
        this.carriedDishes();
    }

    /**
     * 备料
     */
    public abstract void preparation();

    /**
     * 做菜
     */
    public abstract void doing();

    /**
     * 上菜
     */
    public abstract void carriedDishes();

}