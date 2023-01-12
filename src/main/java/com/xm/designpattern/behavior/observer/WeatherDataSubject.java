package com.xm.designpattern.behavior.observer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Observable;

/**
 * 天气数据
 * 1.包含最新的天气情况信息
 * 2.带有观察者集合，使用ArrayList管理
 * 3.当数据有更新时，就主动的调用ArrayList，通知所有的（接入方）就可以看到最新的信息
 *
 * @author XM
 * @date 2023/1/11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeatherDataSubject extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    /**
     * 设置测量数据
     *
     * @param temperature 温度
     * @param pressure    压力
     * @param humidity    湿度
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        // 使用了java 自带的util
        setChanged();
        notifyObservers();
    }
}

