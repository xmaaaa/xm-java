package com.xm.designpattern.behavior.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 今日天气观察者
 *
 * @author xm
 * @date 2023/1/11
 **/
public class TodaySiteObserver implements Observer {

    private float temperature;
    private float pressure;
    private float humidity;

    public void display() {
        System.out.println("---今日天气-天气预报---");
        System.out.println("---今日天气 气温：" + temperature);
        System.out.println("---今日天气 气压：" + pressure);
        System.out.println("---今日天气 湿度：" + humidity);
    }

    /**
     * 更新天气状况
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherDataSubject) {
            WeatherDataSubject weatherDataSubject = (WeatherDataSubject) o;
            this.temperature = weatherDataSubject.getTemperature() * 1.2f;
            this.pressure = weatherDataSubject.getPressure() * 1.2f;
            this.humidity = weatherDataSubject.getHumidity() * 1.2f;
            display();
        }
    }
}

