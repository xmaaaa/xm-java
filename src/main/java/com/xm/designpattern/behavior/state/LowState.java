package com.xm.designpattern.behavior.state;

/**
 * @author XM
 * @date 2023/1/12
 */
public class LowState extends State {

    public LowState() {
        stateName = "不及格";
    }

    @Override
    public void doAction(Context context) {
        int score = context.getScore();
        if (score >= 90) {
            context.setState(new HighState());
        } else if (score >= 60) {
            context.setState(new MiddleState());
        }
    }
}
