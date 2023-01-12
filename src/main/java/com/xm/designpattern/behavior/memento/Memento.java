package com.xm.designpattern.behavior.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 备忘录对象
 *
 * @author XM
 * @date 2023/1/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memento {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 攻击力
     */
    private int vit;
    /**
     * 防御力
     */
    private int def;
}

