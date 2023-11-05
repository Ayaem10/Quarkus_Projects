package org.Auth.Repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.Auth.User;
@ApplicationScoped

public class UsersRepository implements PanacheRepository<User> {

    public User findByName(String name){
        return find("username" , name).firstResult();

    }
}
