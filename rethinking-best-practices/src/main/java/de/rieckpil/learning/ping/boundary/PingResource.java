package de.rieckpil.learning.ping.boundary;

import de.rieckpil.learning.ping.control.PingService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
@Stateless
public class PingResource {

    @Inject
    private PingService pingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {

        System.out.println("incoming call");

        return Response.ok(pingService.getPing()).build();
    }

    @GET
    @Path("script")
    public String calculate() {

        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("JavaScript");

        Object returnValue = null;

        try {

            Bindings bindings = scriptEngine.createBindings();
            bindings.put("FIVE", 5);
            bindings.put("NINE", 9);

            returnValue = scriptEngine.eval("FIVE * NINE", bindings);

        } catch (Exception e) {

        }

        return returnValue.toString();

    }

    @GET
    @Path("async")
    public String doAysncWork() {

        pingService.doAsyncWork();

        return "async";

    }

}

