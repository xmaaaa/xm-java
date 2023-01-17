package com.xm.designpattern.create;

import lombok.ToString;

/**
 * 建造者模式, 可以直接构造字段, 也可以写成抽象类放在子类实现
 *
 * @author hongwan
 * @date 2022/12/14
 */
@lombok.Builder
@ToString
public class Builder {
    private String name;
    private String type;

    public Builder buildName() {
        // 逻辑
        System.out.println("name");
        return this;
    }

    public Builder buildType() {
        System.out.println("type");
        return this;
    }


    public static void main(String[] args) {
        Builder builder = Builder.builder().name("11").type("22").build();
        System.out.println(builder.toString());
        Builder builder2 = builder.buildName().buildType();
        System.out.println(builder2.toString());
    }
}



