package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.User;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Stateless
public class UserService {

    public User getUser(){
        long id = new Date().getTime();

        try {
            TimeUnit.SECONDS.sleep(5);
            return new User(id +  "async", "User " + id);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            return new User(id + "error", "Error " + id);
        }
    }

    @Asynchronous
    public Future<User> getFutureUser(){
        long id = new Date().getTime();
        User user = new User(id + "future", "User " + id);
        return new AsyncResult(user);
    }
}
