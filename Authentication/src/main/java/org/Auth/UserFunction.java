package org.Auth;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.Auth.Exception.BusinessException;



public class UserFunction {
    final static String ADMINROLE  ="admin";
    final static String USERROLE = "user";
    final static String SEPARATOR = ",";



    @Transactional
    public static void addUser(User user) throws BusinessException{
        if (!userExist(user.username)){
            user.password = BcryptUtil.bcryptHash(user.password);
            user.roles = USERROLE;
            user.persist(user);
        }else{
        throw new BusinessException(Response.Status.EXPECTATION_FAILED.getStatusCode(), "There is something wrong please try again");
    }}

    private static void addRole(User user, String role) {
        user.roles = user.roles + SEPARATOR + role ;

    }

    @Transactional
    public static String login(String username, String password) throws BusinessException {
        User existingUser = User.find("username", username).firstResult();
        if(existingUser == null  || !existingUser.password.equals(password)) {

            throw new BusinessException(Response.Status.CONFLICT.getStatusCode(),"  there something wrong with username or password please try again");
        }else{
        return TokenGenerator.generator(username , existingUser.roles);
    }}

    private static boolean userExist(String username) {

        return (User.count("username" , username) > 0 );
    }
    }
