package com.xm.designpattern.behavior.strategy;

/**
 * @author XM
 * @date 2023/1/10
 */
public class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}