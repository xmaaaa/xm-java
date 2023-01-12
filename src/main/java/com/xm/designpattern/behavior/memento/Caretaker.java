package com.xm.designpattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理对象，用于保存游戏角色状态
 *
 * @author XM
 * @date 2023/1/12
 */
public class Caretaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}

