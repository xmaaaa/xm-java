package com.xm.designpattern.behavior.iterator;

/**
 * 迭代器接口
 *
 * @author XM
 * @date 2023/1/11
 */
public interface Iterator {

    boolean hasNext();

    Object next();
}