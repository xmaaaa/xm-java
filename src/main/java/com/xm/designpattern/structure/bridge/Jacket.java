package com.xm.designpattern.structure.bridge;

/**
 * @author hongwan
 * @date 2022/12/23
 */
public class Jacket extends Clothing {

    @Override
    public void personDressCloth(Person person) {
        System.out.println(person.getType() + "穿马甲");
    }
}
