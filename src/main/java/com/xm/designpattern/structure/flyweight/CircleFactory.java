package com.xm.designpattern.structure.flyweight;

import java.util.HashMap;

/**
 * 对象工厂, 可以重复利用已创建的对象
 *
 * @author XM
 * @date 2023/1/5
 */
public class CircleFactory {

    private static final HashMap<String, Circle> circleMap = new HashMap<>();

    public static Circle getCircle(String color) {
        Circle circle = circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}