package de.rieckpil.learning.user.control;

import de.rieckpil.learning.user.entity.JpaUser;
import de.rieckpil.learning.user.entity.User;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.StringTokenizer;

@Named
@Dependent
public class UserProcessor implements ItemProcessor {


    @Override
    public JpaUser processItem(Object line) throws Exception {
        JpaUser user = new JpaUser();

        StringTokenizer tokens = new StringTokenizer((String)
                line, ",");

        user.setId(Long.parseLong(tokens.nextToken()));
        user.setName(tokens.nextToken());
        user.setEmail(tokens.nextToken());

        return user;
    }
}
