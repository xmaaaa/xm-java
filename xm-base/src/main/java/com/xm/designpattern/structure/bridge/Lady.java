package com.xm.designpattern.structure.bridge;

/**
 * @author hongwan
 * @date 2022/12/23
 */
public class Lady extends Person {
    public Lady() {
        setType("女人");
    }

    @Override
    public void dress() {
        Clothing clothing = getClothing();
        clothing.personDressCloth(this);
    }
}
