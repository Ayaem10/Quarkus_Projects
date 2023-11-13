package org.acm.Authentication.Exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException e) {
        Message error = new Message();
        error.setMessage(e.getMessage());
        error.setStatus(e.getStatus());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
