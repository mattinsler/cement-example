package com.mattinsler.cement.example.service;

import com.mattinsler.cement.example.model.SessionEntity;
import com.mattinsler.cement.example.model.UserEntity;
import org.bson.types.ObjectId;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 12:20:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    UserEntity create(String username, String password, String name, String emailAddress);
    boolean exists(String identity);
    boolean exists(ObjectId identity);

    UserEntity getUser(String identity);
    UserEntity getUser(ObjectId identity);

    SessionEntity authenticate(String user, String password);
    SessionEntity getSession(String token);
    void removeSession(UserEntity user);
}
