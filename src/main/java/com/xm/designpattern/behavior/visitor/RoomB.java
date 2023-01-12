package com.xm.designpattern.behavior.visitor;

/**
 * @author XM
 * @date 2023/1/12
 */
public class RoomB implements RoomPart {

    @Override
    public void accept(Friend friend) {
        friend.visit(this);
    }
}