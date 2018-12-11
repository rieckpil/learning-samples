package kryo;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.time.Instant;

@Path("test")
@Stateless
public class KryoResource {

    @GET
    @Path("kryo")
    @Produces("application/x-kryo")
    public Message getMessageViaKryo() {
        return getMessage();
    }

    @POST
    @Path("kryo")
    @Consumes("application/x-kryo")
    public void postMessageViaKryo(final Message message) {
        System.out.println(message.toString());
    }

    @GET
    @Path("jsonb")
    @Produces("application/json")
    public Message getMessageViaJsonb() {
        return getMessage();
    }

    @POST
    @Path("jsonb")
    @Consumes("application/json")
    public void postMessageViaJsonb(final Message message) {
        System.out.println(message.toString());
    }

    @GET
    @Path("fst")
    @Consumes("application/x-fst")
    public Message getMessageViaFst() {
        return getMessage();
    }

    @POST
    @Path("fst")
    @Consumes("application/x-fst")
    public void postMessageViaFst(final Message message) {
        System.out.println(message.toString());
    }

    private Message getMessage() {
        return new Message("Hello World", 12, Instant.now(), 1377.42, false);

    }
}
