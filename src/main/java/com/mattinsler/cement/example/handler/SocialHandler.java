package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.Parameter;
import com.mattinsler.cement.annotation.PathMapping;
import com.mattinsler.cement.example.model.SocialEntity;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.cement.example.service.SocialService;
import com.mattinsler.cement.example.service.UserService;
import com.mattinsler.cement.exception.CementException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 1, 2010
 * Time: 2:58:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocialHandler {
    

    private final UserService _userService;
    private final SocialService _socialService;

    @Inject
    SocialHandler(UserService userService, SocialService socialService) {
        _userService = userService;
        _socialService = socialService;
    }

    private UserEntity getTarget(String userId) {
        UserEntity target = _userService.getUser(userId);
        if (target == null) {
            throw new CementException(HttpServletResponse.SC_OK, "Could not follow friend: friend does not exist");
        }
        return target;
    }

    @Get
    @PathMapping("/follow")
    public void follow(UserEntity user, @Parameter("userId") String friendUserId) {
        _socialService.follow(user, getTarget(friendUserId));
    }

    @Get
    @PathMapping("/unfollow")
    public void unfollow(UserEntity user, @Parameter("userId") String friendUserId) {
        _socialService.unfollow(user, getTarget(friendUserId));
    }

    @Get
    @PathMapping("/block")
    public void block(UserEntity user, @Parameter("userId") String friendUserId) {
        _socialService.block(user, getTarget(friendUserId));
    }

    @Get
    @PathMapping("/unblock")
    public void unblock(UserEntity user, @Parameter("userId") String friendUserId) {
        _socialService.unblock(user, getTarget(friendUserId));
    }

    @Get
    @PathMapping("/following")
    public List<SocialEntity> following(UserEntity user) {
        return _socialService.getFollowing(user);
    }

    @Get
    @PathMapping("/followers")
    public List<SocialEntity> followers(UserEntity user) {
        return _socialService.getFollowers(user);
    }

    @Get
    @PathMapping("/blocking")
    public List<SocialEntity> blocking(UserEntity user) {
        return _socialService.getBlocking(user);
    }

    @Get
    @PathMapping("/blocked-by")
    public List<SocialEntity> blockedBy(UserEntity user) {
        return _socialService.getBlockedBy(user);
    }
}
