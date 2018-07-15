package de.rieckpil.learning.security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class UserIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential instanceof UsernamePasswordCredential) {
            return validate((UsernamePasswordCredential) credential);
        }

        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        if (usernamePasswordCredential.getCaller().equals(Roles.ADMIN) &&
                usernamePasswordCredential.getPassword().compareTo("1234")) {

            return new CredentialValidationResult(
                    new CallerPrincipal(usernamePasswordCredential.getCaller()), new HashSet<>(Arrays.asList(Roles
                    .ADMIN, Roles.ROLE1, Roles.ROLE2)));

        } else if (usernamePasswordCredential.getCaller().equals(Roles.USER)
                && usernamePasswordCredential.getPassword().compareTo("1234")) {

            return new CredentialValidationResult(
                    new CallerPrincipal(usernamePasswordCredential.getCaller()), new HashSet<>(Arrays.asList(Roles
                    .USER, Roles.ROLE3)));
        }

        return CredentialValidationResult.INVALID_RESULT;
    }

}