package de.rieckpil.learning.jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class CustomInMemoryIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        System.out.println(credential.toString());

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        if (login.getCaller().equals("admin@web.de") && login.getPasswordAsString().equals("ADMIN1234")) {
            return new CredentialValidationResult(
                    "admin", new HashSet<>(Arrays.asList("ADMIN_ALL", "USER"))
            );
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
