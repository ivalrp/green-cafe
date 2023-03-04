package org.ival.service;

import org.ival.exception.ValidationException;
import org.ival.model.User;
import org.ival.model.dto.LoginRequest;
import org.ival.model.dto.LoginResponse;
import org.ival.util.FormatUtil;
import org.ival.util.GeneralUtil;
import org.ival.util.JwtUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@ApplicationScoped
public class AuthService {

    public Response login(LoginRequest request) throws NoSuchAlgorithmException {
        if (!FormatUtil.isPassword(request.password)){
            throw new ValidationException("INVALID_PASSWORD");
        }

        Optional<User> userOptional = User.findByLoginName(request.loginName);
        if (userOptional.isEmpty()){
            throw new ValidationException("USERNAME_NOT_FOUND");
        }

        User user = userOptional.get();
        if (!user.comparePassword(GeneralUtil.hashPasword(request.password))){
            throw new ValidationException("WRONG_PASSWORD");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.userData = user;
        loginResponse.token = JwtUtil.generateToken(user);

        return Response.ok(loginResponse).build();
    }
}
