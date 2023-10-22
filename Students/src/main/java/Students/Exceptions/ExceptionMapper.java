package Students.Exceptions;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        errorMessage error = new errorMessage();
        error.setStatus(exception.getStatus());
        error.setMessage(exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error).type(MediaType.APPLICATION_JSON).build();

    }
}
