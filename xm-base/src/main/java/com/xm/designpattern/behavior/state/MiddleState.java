package com.xm.designpattern.behavior.state;

/**
 * @author XM
 * @date 2023/1/12
 */
public class MiddleState extends State {

    public MiddleState() {
        stateName = "中等";
    }

    @Override
    public void doAction(Context context) {
        int score = context.getScore();
        if (score < 60) {
            context.setState(new LowState());
        } else if (score >= 90) {
            context.setState(new HighState());
        }
    }
}