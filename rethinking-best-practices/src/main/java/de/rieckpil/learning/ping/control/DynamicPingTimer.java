package de.rieckpil.learning.ping.control;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

@Startup
@Singleton
public class DynamicPingTimer {

    @Resource
    TimerService timerService;

    @PostConstruct
    public void init() {
        ScheduleExpression expression = new ScheduleExpression();
        expression.second("*/1").minute("*").hour("*");
        timerService.createCalendarTimer(expression);
    }

    @Timeout
    public void execute() {
        System.out.println("----Invoked: " + System.currentTimeMillis());
    }
}
