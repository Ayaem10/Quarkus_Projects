package org.acm.Authentication;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acm.Entities.clients;

@ApplicationScoped
public class AuthRepository implements PanacheRepository<clients> {

    public clients findByname(String username){
        return find("username" , username).firstResult();
    }
}
