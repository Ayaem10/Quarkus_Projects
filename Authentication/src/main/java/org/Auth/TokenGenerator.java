package org.Auth;


import io.smallrye.jwt.build.Jwt;
import org.wildfly.security.password.Password;

import java.util.Arrays;
import java.util.HashSet;

public class TokenGenerator {


    public static String generator(String username , String role){
        String token = Jwt.upn(username).
                groups(new HashSet<>(Arrays.asList(role.split(",")))).sign();
        return token;
    }
}
