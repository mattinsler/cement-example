package com.mattinsler.cement.example.service.mongo;

import com.google.inject.Inject;
import com.mattinsler.guiceymongo.GuiceyCollection;
import com.mattinsler.guiceymongo.guice.annotation.MongoCollection;
import com.mattinsler.cement.example.guice.Collections;
import com.mattinsler.cement.example.model.SocialEntity;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.cement.example.service.SocialService;
import com.mattinsler.cement.util.CollectionUtil;
import com.mattinsler.guiceymongo.guice.annotation.MongoCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 6:02:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class MongoSocialServiceImpl implements SocialService {
    private final GuiceyCollection<SocialEntity> _socialCollection;

    @Inject
    MongoSocialServiceImpl(@MongoCollection(Collections.Social) GuiceyCollection<SocialEntity> socialCollection) {
        _socialCollection = socialCollection;
    }

    public void follow(UserEntity user, UserEntity target) {
        if (_socialCollection.count(
                SocialEntity.newBuilder()
                        .setSourceUser(target.getIdentity())
                        .setDestinationUser(user.getIdentity())
                        .setRelationship(SocialEntity.Relationship.Blocking)
                .build()
        ) == 1) {
            throw new RuntimeException("blah");
        }
        _socialCollection.update(
                QueryBuilder
                        .start(SocialEntity.SourceUserKey).is(user.getIdentity())
                        .and(SocialEntity.DestinationUserKey).is(target.getIdentity())
                        .and(SocialEntity.RelationshipKey).notEquals(SocialEntity.Relationship.Following.name())
                .get(),
                new BasicDBObject("$set",
                        new BasicDBObject(SocialEntity.RelationshipKey, SocialEntity.Relationship.Following.name())
                        .append(SocialEntity.StartingKey, new Date())
                ),
                true,
                false
        );
    }

    public void unfollow(UserEntity user, UserEntity target) {
        _socialCollection.remove(
                QueryBuilder
                        .start(SocialEntity.SourceUserKey).is(user.getIdentity())
                        .and(SocialEntity.DestinationUserKey).is(target.getIdentity())
                        .and(SocialEntity.RelationshipKey).is(SocialEntity.Relationship.Following.name())
                .get()
        );
    }

    public List<SocialEntity> getFollowing(UserEntity user) {
        return getFollowing(user, 0, 0);
    }

    public List<SocialEntity> getFollowing(UserEntity user, int count) {
        return getFollowing(user, 0, count);
    }

    public List<SocialEntity> getFollowing(UserEntity user, int skip, int count) {
        return CollectionUtil.collect(
                _socialCollection.find(
                        SocialEntity.newBuilder()
                                .setSourceUser(user.getIdentity())
                                .setRelationship(SocialEntity.Relationship.Following)
                                .build()
                ).skip(skip).limit(count)
        );
    }

    public List<SocialEntity> getFollowers(UserEntity user) {
        return getFollowers(user, 0, 0);
    }

    public List<SocialEntity> getFollowers(UserEntity user, int count) {
        return getFollowers(user, 0, count);
    }

    public List<SocialEntity> getFollowers(UserEntity user, int skip, int count) {
        return CollectionUtil.collect(
                _socialCollection.find(
                        SocialEntity.newBuilder()
                                .setDestinationUser(user.getIdentity())
                                .setRelationship(SocialEntity.Relationship.Following)
                                .build()
                ).skip(skip).limit(count)
        );
    }

    public boolean isFollowing(ObjectId user, ObjectId target) {
        return _socialCollection.count(
                SocialEntity.newBuilder()
                        .setSourceUser(user)
                        .setDestinationUser(target)
                        .setRelationship(SocialEntity.Relationship.Following)
                .build()
        ) == 1;
    }

    public boolean isFollowing(UserEntity user, ObjectId target) {
        return isFollowing(user.getIdentity(), target);
    }

    public boolean isFollowing(ObjectId user, UserEntity target) {
        return isFollowing(user, target.getIdentity());
    }

    public boolean isFollowing(UserEntity user, UserEntity target) {
        return isFollowing(user.getIdentity(), target.getIdentity());
    }

    public boolean isFollowedBy(ObjectId user, ObjectId target) {
        return isFollowing(target, user);
    }

    public boolean isFollowedBy(UserEntity user, ObjectId target) {
        return isFollowedBy(user.getIdentity(), target);
    }

    public boolean isFollowedBy(ObjectId user, UserEntity target) {
        return isFollowedBy(user, target.getIdentity());
    }

    public boolean isFollowedBy(UserEntity user, UserEntity target) {
        return isFollowedBy(user.getIdentity(), target.getIdentity());
    }

    public void block(UserEntity user, UserEntity target) {
        unfollow(target, user);
        _socialCollection.update(
                QueryBuilder
                        .start(SocialEntity.SourceUserKey).is(user.getIdentity())
                        .and(SocialEntity.DestinationUserKey).is(target.getIdentity())
                        .and(SocialEntity.RelationshipKey).notEquals(SocialEntity.Relationship.Blocking.name())
                .get(),
                new BasicDBObject("$set",
                        new BasicDBObject(SocialEntity.RelationshipKey, SocialEntity.Relationship.Blocking.name())
                        .append(SocialEntity.StartingKey, new Date())
                ),
                true,
                false
        );
    }

    public void unblock(UserEntity user, UserEntity target) {
        _socialCollection.remove(
                QueryBuilder
                        .start(SocialEntity.SourceUserKey).is(user.getIdentity())
                        .and(SocialEntity.DestinationUserKey).is(target.getIdentity())
                        .and(SocialEntity.RelationshipKey).is(SocialEntity.Relationship.Blocking.name())
                .get()
        );
    }

    public List<SocialEntity> getBlocking(UserEntity user) {
        return getBlocking(user, 0, 0);
    }

    public List<SocialEntity> getBlocking(UserEntity user, int count) {
        return getBlocking(user, 0, count);
    }

    public List<SocialEntity> getBlocking(UserEntity user, int skip, int count) {
        return CollectionUtil.collect(
                _socialCollection.find(
                        SocialEntity.newBuilder()
                                .setSourceUser(user.getIdentity())
                                .setRelationship(SocialEntity.Relationship.Blocking)
                        .build()
                ).skip(skip).limit(count)
        );
    }

    public List<SocialEntity> getBlockedBy(UserEntity user) {
        return getBlockedBy(user, 0, 0);
    }

    public List<SocialEntity> getBlockedBy(UserEntity user, int count) {
        return getBlockedBy(user, 0, count);
    }

    public List<SocialEntity> getBlockedBy(UserEntity user, int skip, int count) {
        return CollectionUtil.collect(
                _socialCollection.find(
                        SocialEntity.newBuilder()
                                .setDestinationUser(user.getIdentity())
                                .setRelationship(SocialEntity.Relationship.Blocking)
                        .build()
                ).skip(skip).limit(count)
        );
    }

    public boolean isBlocking(ObjectId user, ObjectId target) {
        return _socialCollection.count(
                SocialEntity.newBuilder()
                        .setSourceUser(user)
                        .setDestinationUser(target)
                        .setRelationship(SocialEntity.Relationship.Blocking)
                .build()
        ) == 1;
    }

    public boolean isBlocking(UserEntity user, ObjectId target) {
        return isBlocking(user.getIdentity(), target);
    }

    public boolean isBlocking(ObjectId user, UserEntity target) {
        return isBlocking(user, target.getIdentity());
    }

    public boolean isBlocking(UserEntity user, UserEntity target) {
        return isBlocking(user.getIdentity(), target.getIdentity());
    }

    public boolean isBlockedBy(ObjectId user, ObjectId target) {
        return isBlocking(target, user);
    }

    public boolean isBlockedBy(UserEntity user, ObjectId target) {
        return isBlockedBy(user.getIdentity(), target);
    }

    public boolean isBlockedBy(ObjectId user, UserEntity target) {
        return isBlockedBy(user, target.getIdentity());
    }

    public boolean isBlockedBy(UserEntity user, UserEntity target) {
        return isBlockedBy(user.getIdentity(), target.getIdentity());
    }
}
