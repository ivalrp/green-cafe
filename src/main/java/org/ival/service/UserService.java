package org.ival.service;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import org.ival.exception.ValidationException;
import org.ival.model.User;
import org.ival.model.dto.UserRequest;
import org.ival.util.FormatUtil;
import org.ival.util.GeneralUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    public Response post(UserRequest request) throws NoSuchAlgorithmException {
        if (!User.isEmptyByLoginName(request.loginName)){
            throw new ValidationException("Login_NAME_EXIST");
        }

        if (!FormatUtil.isPassword(request.password)){
            throw new ValidationException("INVALID_PASSWORD");
        }

        if (!FormatUtil.isEmail(request.email)){
            throw new ValidationException("BAD_REQUEST");
        }

        if (!FormatUtil.isAlphabet(request.fullName)){
            throw new ValidationException("WRONG_NAME");
        }

        if (!FormatUtil.isPhoneNumber(request.mobilePhoneNumber) ||
                !FormatUtil.isPhoneNumber(request.workPhoneNumber)){
            throw new ValidationException("NUMBER");
        }

        persistUser(request, null);
        return Response.ok(new HashMap<>()).build();
    }

    public Response put(UserRequest request, String userId) throws NoSuchAlgorithmException {
        if (!FormatUtil.isPassword(request.password)){
            throw new ValidationException("INVALID_PASSWORD");
        }

        if (!FormatUtil.isEmail(request.email)){
            throw new ValidationException("BAD_REQUEST");
        }

        if (!FormatUtil.isAlphabet(request.fullName)){
            throw new ValidationException("WRONG_NAME");
        }

        if (!FormatUtil.isPhoneNumber(request.mobilePhoneNumber) ||
                !FormatUtil.isPhoneNumber(request.workPhoneNumber)){
            throw new ValidationException("NUMBER");
        }

        persistUser(request, userId);
        return Response.ok(new HashMap<>()).build();
    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public Response delete(String userId){
        User.deleteById(userId);
        return Response.ok(new HashMap<>()).build();
    }

    public Response get(String loginName){
        Optional<User> userOptional = User.findByLoginName(loginName);
        if (userOptional.isEmpty()){
            throw new ValidationException("USER_NOT_FOUND");
        }

        return Response.ok(userOptional.get()).build();
    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public User persistUser(UserRequest request, String userId) throws NoSuchAlgorithmException {
        User user;

        if (userId == null){
            user = new User();
        } else {
            Optional<User> userOptional = User.findByIdOptional(userId);
            if (userOptional.isEmpty()){
                throw new ValidationException("USER_NOT_FOUND");
            }
            user = userOptional.get();
        }

        user.setLoginName(request.loginName);
        user.setPassword(GeneralUtil.hashPasword(request.password));
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setAddress(request.address);
        user.setMobilePhoneNumber(request.mobilePhoneNumber);
        user.setWorkPhoneNumber(request.workPhoneNumber);

        User.persist(user);

        return user;
    }

    @Transactional
    @TransactionConfiguration(timeout = 30)
    public User persistPost(UserRequest request) throws NoSuchAlgorithmException {
        Optional<User> userOptional = User.findByLoginName(request.loginName);
        User user;

        if (userOptional.isEmpty()){
            user = new User();
        } else {
            user = userOptional.get();
        }

        user.setLoginName(request.loginName);
        user.setPassword(GeneralUtil.hashPasword(request.password));
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setAddress(request.address);
        user.setMobilePhoneNumber(request.mobilePhoneNumber);
        user.setWorkPhoneNumber(request.workPhoneNumber);

        User.persist(user);

        return user;
    }





}
