package sample;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class SampleManager {

    public String getName() {
        return UUID.randomUUID().toString();
    }
}
