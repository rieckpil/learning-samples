package de.rieckpil.learning.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class AuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private UserIdentityStore identityStore;

    /** @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
                                                HttpMessageContext httpMessageContext) throws AuthenticationException {

        if (httpMessageContext.isAuthenticationRequest()) {

            Credential credential = httpMessageContext.getAuthParameters().getCredential();

            if (!(credential instanceof CallerOnlyCredential)) {
                throw new IllegalStateException("Invalid mechanism");
            }

            CallerOnlyCredential callerOnlyCredential = (CallerOnlyCredential) credential;

            if ("user".equals(callerOnlyCredential.getCaller())) {
                return
                        httpMessageContext.notifyContainerAboutLogin
                                (callerOnlyCredential.getCaller(), new HashSet<>
                                        (Arrays.asList("role1", "role2")));
            } else {
                throw new AuthenticationException();
            }

        }

        return httpMessageContext.doNothing();
    }*/

   @Override
   public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
                                               HttpMessageContext httpMessageContext) throws AuthenticationException {

       if (httpMessageContext.isAuthenticationRequest()) {

           Credential credential = httpMessageContext.getAuthParameters().getCredential();
           if (!(credential instanceof UsernamePasswordCredential)) {
               throw new IllegalStateException("Invalid mechanism");
           }

           return httpMessageContext.notifyContainerAboutLogin
                   (identityStore.validate(credential));
       }

       return httpMessageContext.doNothing();
   }

}