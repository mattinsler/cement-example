package com.mattinsler.cement.example.service;

import net.oauth.*;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 11, 2010
 * Time: 10:55:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OAuthService {
    OAuthConsumer createConsumer(String author, String description, String callback);

    OAuthConsumer getConsumer(OAuthMessage message) throws IOException, OAuthProblemException;
    void generateRequestToken(OAuthAccessor accessor) throws OAuthException;
    OAuthAccessor getAccessor(OAuthMessage message) throws IOException, OAuthProblemException;
    void generateAccessToken(OAuthAccessor accessor) throws OAuthException;
    void markAsAuthorized(OAuthAccessor accessor, String userId) throws OAuthException;
}
