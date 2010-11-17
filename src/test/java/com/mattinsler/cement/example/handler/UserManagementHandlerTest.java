package com.mattinsler.cement.example.handler;

import atunit.AtUnit;
import atunit.Container;
import atunit.Unit;
import com.google.inject.Inject;
import com.mattinsler.test.BasicTest;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 29, 2010
 * Time: 4:29:35 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(AtUnit.class)
@Container(Container.Option.GUICE)
public class UserManagementHandlerTest extends BasicTest {
    @Unit
    @Inject
    UserManagementHandler handler;

    private final String username = "username";
    private final String password = "password";
    private final String name = "name";
    private final String emailAddress = "emailAddress";

    @Test
    public void testCreateUser() {
        handler.createUser(username, password, name, emailAddress);
    }

    @Test(expected = UserManagementHandler.CreateUserException.class)
    public void testCreateUser_failure() {
        handler.createUser(username, password, name, emailAddress);
        handler.createUser(username, password, name, emailAddress);
    }
}
