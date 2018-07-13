package de.rieckpil.learning.user.boundary;

import de.rieckpil.learning.user.entity.Profile;
import de.rieckpil.learning.user.entity.ProfileType;
import de.rieckpil.learning.user.entity.User;
import de.rieckpil.learning.user.entity.UserProfile;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@RequestScoped
public class UserResource {

    @Inject
    private User user;

    @Inject
    @Profile(ProfileType.ADMIN)
    private UserProfile userProfileAdmin;

    @Inject
    @Profile(ProfileType.OPERATOR)
    private UserProfile userProfileOperator;

    @Inject
    private Event<User> userEvent;

    @GET
    @Path("getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser() {
        return Response.ok(user).build();
    }

    @GET
    @Path("getProfileAdmin")
    public Response getProfileAdmin() {
        fireUserEvents(userProfileAdmin.type());
        return Response.ok().build();
    }

    @GET
    @Path("getProfileOperator")
    public Response getProfileOperator() {
        fireUserEvents(userProfileOperator.type());
        return Response.ok().build();
    }

    private ProfileType fireUserEvents(ProfileType type) {
        userEvent.fire(user);
        userEvent.fireAsync(user);
        return type;
    }

    public void sendUserNotification(@Observes User user) {
        System.out.println("sendUserNotification: " + user);
    }

    public void sendUserNotificationAsync(@ObservesAsync User user) {
        System.out.println("sendUserNotificationAsync: " + user);
    }
}
