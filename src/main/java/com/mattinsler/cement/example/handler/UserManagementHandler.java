package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.annotation.Contract;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.annotation.PathMapping;
import com.mattinsler.cement.example.contract.AddressContract;
import com.mattinsler.cement.example.service.UserService;
import com.mattinsler.cement.exception.CementException;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 2:46:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserManagementHandler {
    public static class CreateUserException extends CementException {
        CreateUserException() {
            super(HttpServletResponse.SC_OK);
        }
    }

    private final UserService _userService;

    @Inject
    UserManagementHandler(UserService userService) {
        _userService = userService;
    }

    @Get
    @PathMapping("/create")
    @Contract(AddressContract.class)
    public void createUser(@Parameter("user") String username,
                           @Parameter("password") String password,
                           @Parameter("name") String name,
                           @Parameter("email") String emailAddress) {
        if (_userService.create(username, password, name, emailAddress) == null) {
            throw new CreateUserException();
        }
    }
}
