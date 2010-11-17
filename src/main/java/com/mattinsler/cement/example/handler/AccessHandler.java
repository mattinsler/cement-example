package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.annotation.PathMapping;
import com.mattinsler.cement.annotation.Post;
import com.mattinsler.cement.example.model.SessionEntity;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.cement.example.service.UserService;
import com.mattinsler.cement.exception.CementUnauthorizedException;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 12:17:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccessHandler {
    private final UserService _userService;

    @Inject
    AccessHandler(UserService userService) {
        _userService = userService;
    }

    @Get
    @Post
    @PathMapping("/login")
    public String login(@Parameter("user") String user, @Parameter("password") String password) {
        SessionEntity session = _userService.authenticate(user, password);
        if (session == null) {
            throw new CementUnauthorizedException("The specified username and password does not exist");
        }
        return session.getToken().toString();
    }

    @Get
    @PathMapping("/logout")
    public void logout(UserEntity user) {
        _userService.removeSession(user);
    }
}
