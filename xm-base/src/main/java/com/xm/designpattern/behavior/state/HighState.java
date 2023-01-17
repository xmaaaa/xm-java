package com.xm.designpattern.behavior.state;

/**
 * @author XM
 * @date 2023/1/12
 */
public class HighState extends State {

    public HighState() {
        stateName = "优秀";
    }

    @Override
    public void doAction(Context context) {
        int score = context.getScore();
        if (score < 60) {
            context.setState(new LowState());
        } else if (score < 90) {
            context.setState(new MiddleState());
        }
    }
}