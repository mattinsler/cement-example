package com.mattinsler.cement.example.service;

import com.mattinsler.cement.example.model.SocialEntity;
import com.mattinsler.cement.example.model.UserEntity;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 5:56:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SocialService {
    void follow(UserEntity user, UserEntity target);
    void unfollow(UserEntity user, UserEntity target);

    List<SocialEntity> getFollowing(UserEntity user);
    List<SocialEntity> getFollowing(UserEntity user, int count);
    List<SocialEntity> getFollowing(UserEntity user, int skip, int count);

    List<SocialEntity> getFollowers(UserEntity user);
    List<SocialEntity> getFollowers(UserEntity user, int count);
    List<SocialEntity> getFollowers(UserEntity user, int skip, int count);

    boolean isFollowing(ObjectId user, ObjectId target);
    boolean isFollowing(UserEntity user, ObjectId target);
    boolean isFollowing(ObjectId user, UserEntity target);
    boolean isFollowing(UserEntity user, UserEntity target);

    boolean isFollowedBy(ObjectId user, ObjectId target);
    boolean isFollowedBy(UserEntity user, ObjectId target);
    boolean isFollowedBy(ObjectId user, UserEntity target);
    boolean isFollowedBy(UserEntity user, UserEntity target);

    void block(UserEntity user, UserEntity target);
    void unblock(UserEntity user, UserEntity target);

    List<SocialEntity> getBlocking(UserEntity user);
    List<SocialEntity> getBlocking(UserEntity user, int count);
    List<SocialEntity> getBlocking(UserEntity user, int skip, int count);

    List<SocialEntity> getBlockedBy(UserEntity user);
    List<SocialEntity> getBlockedBy(UserEntity user, int count);
    List<SocialEntity> getBlockedBy(UserEntity user, int skip, int count);

    boolean isBlocking(ObjectId user, ObjectId target);
    boolean isBlocking(UserEntity user, ObjectId target);
    boolean isBlocking(ObjectId user, UserEntity target);
    boolean isBlocking(UserEntity user, UserEntity target);

    boolean isBlockedBy(ObjectId user, ObjectId target);
    boolean isBlockedBy(UserEntity user, ObjectId target);
    boolean isBlockedBy(ObjectId user, UserEntity target);
    boolean isBlockedBy(UserEntity user, UserEntity target);
}
