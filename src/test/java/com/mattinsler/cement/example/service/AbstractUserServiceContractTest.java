package com.mattinsler.cement.example.service;

import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.test.BasicContractTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 2:47:49 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractUserServiceContractTest extends BasicContractTest<UserService> {
    private final String username = "username";
    private final String password = "password";
    private final String name = "name";
    private final String emailAddress = "emailAddress";

    interface Method<R> {
        R execute();
    }

    private String getMethodCall() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        return stack[2].getMethodName();
    }

    private <R> R time(Method<R> method) {
        long start = System.currentTimeMillis();
        R r = method.execute();
        long end = System.currentTimeMillis();
        System.out.printf("%s: [%s,%s] %sms\n", getMethodCall(), start, end, end - start);
        return r;
    }

    @Test
    public void testCreateUser() {
        UserEntity user = time(new Method<UserEntity>() {
            public UserEntity execute() {
                return contract.create(username, password, name, emailAddress);
            }
        });

        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(password, user.getPassword());
        Assert.assertEquals(name, user.getName());
        Assert.assertEquals(emailAddress, user.getEmailAddress());
    }

    @Test
    public void testExistsString() {
        final UserEntity user = contract.create(username, password, name, emailAddress);

        boolean exists = time(new Method<Boolean>() {
            public Boolean execute() {
                return contract.exists(user.getIdentity().toString());
            }
        });

        Assert.assertTrue(exists);
    }

    @Test
    public void testExistsObjectId() {
        final UserEntity user = contract.create(username, password, name, emailAddress);

        boolean exists = time(new Method<Boolean>() {
            public Boolean execute() {
                return contract.exists(user.getIdentity());
            }
        });

        Assert.assertTrue(exists);
    }

    @Test
    public void testNotExists() {
        boolean exists = time(new Method<Boolean>() {
            public Boolean execute() {
                return contract.exists("non-existant");
            }
        });

        Assert.assertFalse(exists);
    }
}
