package sample;

import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

public class Monitor {

    public void onSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) String msg) {
        System.out.println(msg);
    }
}
