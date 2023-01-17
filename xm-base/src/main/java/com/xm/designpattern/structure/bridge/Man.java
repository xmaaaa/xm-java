package com.xm.designpattern.structure.bridge;

/**
 * @author hongwan
 * @date 2022/12/23
 */
public class Man extends Person {

    public Man() {
        setType("男人");
    }

    @Override
    public void dress() {
        Clothing clothing = getClothing();
        clothing.personDressCloth(this);
    }
}
