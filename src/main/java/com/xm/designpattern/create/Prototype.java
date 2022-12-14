package com.xm.designpattern.create;

import lombok.*;

/**
 * 原型模式, 主要是利用Cloneable 接口实现深浅拷贝
 *
 * @author hongwan
 * @date 2022/12/14
 */
@Setter
@Getter
public class Prototype implements Cloneable {

    private Name name;


    /**
     * 原版clone, 浅拷贝, 除基础类型之外只拷贝引用
     * 改进版深拷贝，对每个字段进行处理
     *
     * @return
     */
    @SneakyThrows
    @Override
    public Prototype clone() {
        try {
            // 浅拷贝
            Prototype clone = (Prototype) super.clone();
            // 深拷贝
            clone.setName(new Name(name.getName()));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new Exception();
        }
    }

    @AllArgsConstructor
    @Setter
    @Getter
    static class Name {
        private String name;
    }
}
