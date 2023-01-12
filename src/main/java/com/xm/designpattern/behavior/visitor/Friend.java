package com.xm.designpattern.behavior.visitor;

/**
 * 访问者接口
 *
 * @author XM
 * @date 2023/1/12
 */
public interface Friend {
    /**
     * 访问房间A
     *
     * @param roomA
     */
    void visit(RoomA roomA);

    /**
     * 访问房间B
     *
     * @param roomB
     */
    void visit(RoomB roomB);
}