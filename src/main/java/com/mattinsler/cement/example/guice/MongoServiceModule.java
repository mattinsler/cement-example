package com.mattinsler.cement.example.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.mattinsler.cement.example.service.LogService;
import com.mattinsler.cement.example.service.RequestLogService;
import com.mattinsler.cement.example.service.SocialService;
import com.mattinsler.cement.example.service.UserService;
import com.mattinsler.cement.example.service.mongo.MongoLogServiceImpl;
import com.mattinsler.cement.example.service.mongo.MongoRequestLogServiceImpl;
import com.mattinsler.cement.example.service.mongo.MongoSocialServiceImpl;
import com.mattinsler.cement.example.service.mongo.MongoUserServiceImpl;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 26, 2010
 * Time: 11:13:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class MongoServiceModule implements Module {
    public void configure(Binder binder) {
        binder.bind(LogService.class).to(MongoLogServiceImpl.class);
        binder.bind(RequestLogService.class).to(MongoRequestLogServiceImpl.class);
        binder.bind(UserService.class).to(MongoUserServiceImpl.class);
        binder.bind(SocialService.class).to(MongoSocialServiceImpl.class);
    }
}
