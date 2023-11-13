package org.acm.Authentication;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acm.Authentication.Exceptions.BusinessException;
import org.acm.Entities.clients;


public class AuthFunction {

    final static String USERROLE = "user";
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public static void addClient(clients client) throws BusinessException {
        if (!ExistClient(client.username)){
            client.roles = USERROLE;
            client.password = BcryptUtil.bcryptHash(client.password);
            client.persist(client);
        }else {
            throw new BusinessException(Response.Status.EXPECTATION_FAILED.getStatusCode(),
                    "There Something Wrong Please Try again");
        }
    }

    private static boolean ExistClient(String username) {
        return (clients.count("username" , username)>0);
    }
}
