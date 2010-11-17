package com.mattinsler.cement.example.handler;

import com.google.inject.Inject;
import com.mattinsler.cement.annotation.Get;
import com.mattinsler.cement.annotation.PathMapping;
import com.mattinsler.cement.annotation.Post;
import com.mattinsler.cement.example.service.OAuthService;
import net.oauth.*;
import net.oauth.server.OAuthServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 11, 2010
 * Time: 10:51:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class OAuthHandler {
    private final OAuthService _oauthService;

    private final OAuthValidator _validator = new SimpleOAuthValidator();

    @Inject
    OAuthHandler(OAuthService oauthService) {
        _oauthService = oauthService;
    }

    private static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response, boolean sendBody) throws IOException, ServletException {
        String realm = (request.isSecure() ? "https://" : "http://") + request.getLocalName();
        OAuthServlet.handleException(response, e, realm, true);
    }

    private void sendToAuthorizePage(HttpServletRequest request, HttpServletResponse response, OAuthAccessor accessor) throws IOException, ServletException {
        String callback = request.getParameter("oauth_callback");
        if(callback == null || callback.length() <= 0) {
            callback = "none";
        }
        String consumer_description = (String)accessor.consumer.getProperty("description");
        request.setAttribute("CONS_DESC", consumer_description);
        request.setAttribute("CALLBACK", callback);
        request.setAttribute("TOKEN", accessor.requestToken);
        request.getRequestDispatcher("/authorize.jsp").forward(request, response);
    }

    private void returnToConsumer(HttpServletRequest request, HttpServletResponse response, OAuthAccessor accessor) throws IOException, ServletException{
        // send the user back to site's callBackUrl
        String callback = request.getParameter("oauth_callback");
        if("none".equals(callback) && accessor.consumer.callbackURL != null && accessor.consumer.callbackURL.length() > 0) {
            // first check if we have something in our properties file
            callback = accessor.consumer.callbackURL;
        }

        if("none".equals(callback)) {
            // no call back it must be a client
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.println("You have successfully authorized '" + accessor.consumer.getProperty("description") + "'. Please close this browser window and click continue in the client.");
            out.close();
        } else {
            // if callback is not passed in, use the callback from config
            if(callback == null || callback.length() <=0) {
                callback = accessor.consumer.callbackURL;
            }
            String token = accessor.requestToken;
            if (token != null) {
                callback = OAuth.addParameters(callback, "oauth_token", token);
            }

            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", callback);
        }
    }

    @Get
    @Post
    @PathMapping("/request-token")
    public void requestToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            OAuthMessage message = OAuthServlet.getMessage(request, null);
            OAuthConsumer consumer = _oauthService.getConsumer(message);
            OAuthAccessor accessor = new OAuthAccessor(consumer);

            _validator.validateMessage(message, accessor);
            {
                // Support the 'Variable Accessor Secret' extension
                // described in http://oauth.pbwiki.com/AccessorSecret
                String secret = message.getParameter("oauth_accessor_secret");
                if (secret != null) {
                    accessor.setProperty(OAuthConsumer.ACCESSOR_SECRET, secret);
                }
            }
            // generate request_token and secret
            _oauthService.generateRequestToken(accessor);

            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            OAuth.formEncode(OAuth.newList("oauth_token", accessor.requestToken, "oauth_token_secret", accessor.tokenSecret), out);
            out.close();
        } catch (Exception e) {
            handleException(e, request, response, true);
        }
    }

    @Get
    @PathMapping("/authorize")
    public void authorizeGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            OAuthMessage message = OAuthServlet.getMessage(request, null);
            OAuthAccessor accessor = _oauthService.getAccessor(message);

            if (Boolean.TRUE.equals(accessor.getProperty("authorized"))) {
                // already authorized send the user back
                returnToConsumer(request, response, accessor);
            } else {
                sendToAuthorizePage(request, response, accessor);
            }
        } catch (Exception e){
            handleException(e, request, response, true);
        }
    }

    @Post
    @PathMapping("/authorize")
    public void authorizePost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            OAuthMessage message = OAuthServlet.getMessage(request, null);
            OAuthAccessor accessor = _oauthService.getAccessor(message);

            String userId = request.getParameter("userId");
            if(userId == null){
                sendToAuthorizePage(request, response, accessor);
            }
            // set userId in accessor and mark it as authorized
            _oauthService.markAsAuthorized(accessor, userId);

            returnToConsumer(request, response, accessor);
        } catch (Exception e){
            handleException(e, request, response, true);
        }
    }

    @Get
    @Post
    @PathMapping("/access-token")
    public void accessToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            OAuthMessage message = OAuthServlet.getMessage(request, null);
            OAuthAccessor accessor = _oauthService.getAccessor(message);
            _validator.validateMessage(message, accessor);

            // make sure token is authorized
            if (!Boolean.TRUE.equals(accessor.getProperty("authorized"))) { 
                throw new OAuthProblemException("permission_denied");
            }
            // generate access token and secret
            _oauthService.generateAccessToken(accessor);

            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            OAuth.formEncode(OAuth.newList("oauth_token", accessor.accessToken, "oauth_token_secret", accessor.tokenSecret), out);
            out.close();
        } catch (Exception e) {
            handleException(e, request, response, true);
        }
    }
}
