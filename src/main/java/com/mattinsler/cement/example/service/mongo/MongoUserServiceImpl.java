package com.mattinsler.cement.example.service.mongo;

import com.google.inject.Inject;
import com.mattinsler.cement.example.guice.Collections;
import com.mattinsler.cement.example.model.SessionEntity;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.cement.example.service.UserService;
import com.mattinsler.cement.util.StringUtil;
import com.mattinsler.guiceymongo.GuiceyCollection;
import com.mattinsler.guiceymongo.guice.annotation.MongoCollection;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 12:33:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class MongoUserServiceImpl implements UserService {
    private final GuiceyCollection<UserEntity> _userCollection;
    private final GuiceyCollection<SessionEntity> _sessionCollection;

    @Inject
    MongoUserServiceImpl(@MongoCollection(Collections.User) GuiceyCollection<UserEntity> userCollection,
                         @MongoCollection(Collections.Session) GuiceyCollection<SessionEntity> sessionCollection) {
        _userCollection = userCollection;
        _sessionCollection = sessionCollection;
    }

    public UserEntity create(String username, String password, String name, String emailAddress) {
        if (StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password) || StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(emailAddress)) {
            return null;
        }
        return _userCollection.findAndModify(
                UserEntity.newBuilder()
                        .setUsername(username)
                .build(),
                UserEntity.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .setName(name)
                        .setEmailAddress(emailAddress)
                .build(),
                null,
                true,
                null,
                true
        );
    }

    public boolean exists(String identity) {
        try {
            return exists(new ObjectId(identity));
        } catch (IllegalArgumentException e) { // invalid ObjectId
            return false;
        }
    }

    public boolean exists(ObjectId identity) {
        return _userCollection.count(UserEntity.newBuilder().setIdentity(identity).build()) > 0;
    }

    public boolean existsByUsername(String username) {
        return _userCollection.find(new BasicDBObject(UserEntity.UsernameKey, username)).count() > 0;
    }

    public UserEntity getUser(String identity) {
        return getUser(new ObjectId(identity));
    }

    public UserEntity getUser(ObjectId identity) {
        return _userCollection.findOne(new BasicDBObject(UserEntity.IdentityKey, identity));
    }

    public SessionEntity authenticate(String username, String password) {
        UserEntity user = _userCollection.findOne(
                new BasicDBObject(UserEntity.UsernameKey, username)
                .append(UserEntity.PasswordKey, password)
        );
        if (user == null) {
            return null;
        }
        return _sessionCollection.findAndModify(
                new BasicDBObject(SessionEntity.UserKey, user.getIdentity()),
                new BasicDBObject("$set",
                        new BasicDBObject(SessionEntity.UserKey, user.getIdentity())
                ),
                null, true, null, true
        );
    }

    public SessionEntity getSession(String token) {
        return _sessionCollection.findOne(SessionEntity.newBuilder().setToken(new ObjectId(token)).build());
    }

    public void removeSession(UserEntity user) {
        _sessionCollection.remove(new BasicDBObject(SessionEntity.UserKey, user.getIdentity()));
    }
}
