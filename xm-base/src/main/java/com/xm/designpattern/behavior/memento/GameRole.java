package com.xm.designpattern.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 游戏角色
 *
 * @author zrj
 * @since 2021/11/8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRole {

    private String name;
    private int vit;
    private int def;

    /**
     * 创建Memento,即根据当前的状态得到 Memento
     *
     * @return
     */
    public Memento createMemento() {
        return new Memento(name, vit, def);
    }

    /**
     * 从备忘录对象，恢复 gameRole 的状态
     *
     * @param memento
     */
    public void recoverGameRoleFromMemento(Memento memento) {
        this.name = memento.getName();
        this.vit = memento.getVit();
        this.def = memento.getDef();
    }

    /**
     * 显示当前游戏角色的状态
     */
    public void display() {
        System.out.println("游戏角色：" + name + " 攻击力：" + this.vit + " 防御力：" + this.def);
    }
}

