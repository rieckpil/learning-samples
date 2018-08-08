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
        expression.second("*/10").minute("*").hour("*");

        System.out.println(expression.toString());

        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setPersistent(false);

        timerService.createCalendarTimer(expression, timerConfig);
    }

    @Timeout
    public void execute() {
        System.out.println("----Invoked: " + System.currentTimeMillis());
    }
}
