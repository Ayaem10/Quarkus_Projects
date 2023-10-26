package org.acme.Exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;

public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<MyException> {


    @Override
    public Response toResponse(MyException exception) {
        ErrorMessage error = new ErrorMessage();
        error.setStatus(exception.getStatus());
        error.setMessage(exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error).type(MediaType.APPLICATION_JSON).build();

    }
}
