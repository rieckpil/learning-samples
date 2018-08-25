package de.rieckpil.learning;

import de.rieckpil.learning.control.CarCreationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarCreationExceptionMapper implements ExceptionMapper<CarCreationException> {

    @Override
    public Response toResponse(CarCreationException exception) {
        return Response.serverError()
                .header("X-Car-Error", exception.getMessage())
                .entity(exception.getMessage()).build();
    }
}
