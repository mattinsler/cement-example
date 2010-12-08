package com.mattinsler.cement.example.guice;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.mattinsler.cement.CementRequestParameters;
import com.mattinsler.cement.example.annotation.Authenticated;
import com.mattinsler.cement.example.model.SessionEntity;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.cement.example.service.UserService;
import com.mattinsler.cement.exception.CementUnauthorizedException;
import com.mattinsler.cement.util.ServletUtil;
import com.mattinsler.guiceytools.AbstractProviderModule;
import com.mattinsler.guiceytools.ProviderModule;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 11, 2010
 * Time: 9:57:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthenticatedUserProviderModule extends ProviderModule<UserEntity> {
    private UserService _userService;
    private Provider<HttpServletRequest> _requestProvider;
    private Provider<CementRequestParameters> _parametersProvider;

    public AuthenticatedUserProviderModule() {
        super(Key.get(UserEntity.class, Authenticated.class));
    }

    @Inject
    void injectDependencies(UserService userService, Provider<HttpServletRequest> requestProvider, Provider<CementRequestParameters> parametersProvider) {
        _userService = userService;
        _requestProvider = requestProvider;
        _parametersProvider = parametersProvider;
    }

    public void configure(Binder binder) {
        binder.skipSources(AuthenticatedUserProviderModule.class).bind(super.key).toProvider(this);
    }

    public UserEntity get() {
        String token = ServletUtil.getCookie(_requestProvider.get(), "token");
        if (token == null) {
            token = _parametersProvider.get().get("token");
        }
        if (token != null) {
            SessionEntity session = _userService.getSession(token);
            if (session != null) {
                return _userService.getUser(session.getUser());
            }
        } else {

        }
        throw new CementUnauthorizedException("You need to be logged in to perform that action");
    }
}
