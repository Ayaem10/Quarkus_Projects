package org.Auth;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.Auth.Exception.BusinessException;
import org.Auth.Repository.UsersRepository;

import java.util.List;

@Path("user")
public class AuthResource {
    @Inject
    UsersRepository usersRepository;


    @POST
    @PermitAll
    @Path("/register")
    public Response addUser(User user) throws BusinessException {
        UserFunction.addUser(user);
        return Response.ok().build();
    }

    @GET
    public Response getAllUsers(){
        List<User> users = User.listAll();
        return Response.ok(users).build();
    }
    @GET
    @Path("username/{username}")
    public Response getByUsername(@PathParam("username") String name)throws BusinessException{
        User user = usersRepository.findByName(name);
        if (user==null){
            throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(), "There is no user with this name " + name);
        }else {
        return Response.ok(user).build();
    }}

@GET
@RolesAllowed("user")
@Path("/me")
public String me(@Context SecurityContext securityContext) {
    return securityContext.getUserPrincipal().getName();
}




}
