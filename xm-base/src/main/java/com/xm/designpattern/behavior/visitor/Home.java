package com.xm.designpattern.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 被访问者
 *
 * @author XM
 * @date 2023/1/12
 */
public class Home implements RoomPart {

    /**
     * 房子中包含房间
     */
    private final List<RoomPart> list = new ArrayList<>();

    public void addRoom(RoomPart room) {
        list.add(room);
    }

    @Override
    public void accept(Friend friend) {
        for (RoomPart room : list) {
            room.accept(friend);
        }
    }
}