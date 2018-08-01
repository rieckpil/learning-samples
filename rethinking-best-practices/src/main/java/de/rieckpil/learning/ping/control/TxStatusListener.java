package de.rieckpil.learning.ping.control;

import javax.ejb.EJBContext;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

public class TxStatusListener {

    public void onInProgress(@Observes String msg) {
        System.out.println("In progress: " + msg);
    }

    public void onSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) String msg) {
        System.out.println("On success: " + msg);
    }

    public void onFailure(@Observes(during = TransactionPhase.AFTER_FAILURE) String msg) {
        System.out.println("On failure: " + msg);
    }

    public void beforeCompletion(@Observes(during = TransactionPhase.BEFORE_COMPLETION) EJBContext sc) {
        System.out.println("Before completion");
    }
}
