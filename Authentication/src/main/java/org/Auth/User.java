package org.Auth;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
@UserDefinition
public class User extends PanacheEntity  {

    @Column(unique = true)
    @Username
    public String username;

    @Password
    public String password;

    @Roles
    public String roles;





}
