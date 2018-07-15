package de.rieckpil.learning.security;

import javax.annotation.security.RunAs;
import javax.inject.Named;

@Named
@RunAs(Roles.ROLE2)
public class Role2Executor implements RoleExecutable {

    @Override
    public void run(Executable executable) throws Exception {
        executable.execute();
    }
}