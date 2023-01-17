package com.xm.designpattern.structure.composite;

/**
 * 抽象构件角色类
 * 此处为安全性合成模式, 树叶类和树枝类将具有不同的接口
 * 还有透明性模型，透明性提供相同接口, 但叶子节点不能调用部分接口
 *
 * @author XM
 * @date 2023/1/4
 */
public interface Component {

    /**
     * 输出组建自身的名称
     */
    void printStruct(String preStr);
}
