package com.xm;

import lombok.SneakyThrows;

/**
 * @author XM
 * @date 2021/6/9
 */
public class Test {


    @SneakyThrows
    public static void main(String[] args) {
        int a = 123214;
        String s = Integer.toString(a);
        String s1 = String.valueOf(a);
        System.out.println(s);
    }




}