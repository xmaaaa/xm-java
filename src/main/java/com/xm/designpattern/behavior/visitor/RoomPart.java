package com.xm.designpattern.behavior.visitor;

/**
 * 被访问对象
 *
 * @author XM
 * @date 2023/1/12
 */
public interface RoomPart {
    /**
     * 接收访问
     *
     * @param friend
     */
    void accept(Friend friend);
}