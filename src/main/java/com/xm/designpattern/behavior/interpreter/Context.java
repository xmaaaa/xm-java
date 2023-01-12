package com.xm.designpattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XM
 * @date 2023/1/12
 */
public class Context {
    /**
     * 存储变量及对应的值
     */
    private final Map<VariableExpression, Integer> map = new HashMap<>();

    /**
     * 添加变量
     *
     * @param variable
     * @param value
     */
    public void addVar(VariableExpression variable, Integer value) {
        map.put(variable, value);
    }

    /**
     * 根据变量获取对应的值
     *
     * @param variable
     * @return
     */
    public int getValue(VariableExpression variable) {
        return map.get(variable);
    }
}