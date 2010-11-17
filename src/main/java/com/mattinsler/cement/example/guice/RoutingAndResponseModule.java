package com.mattinsler.cement.example.guice;

import com.mattinsler.cement.example.handler.*;
import com.mattinsler.cement.guice.CementServletModule;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 23, 2010
 * Time: 2:03:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class RoutingAndResponseModule extends CementServletModule {
    @Override
    protected void configureCement() {
        // routing
        serveCement("/introspect").with(IntrospectionHandler.class);

        serveCement("/access/*").with(AccessHandler.class);
//        serveCement("/access/oauth/*").with(OAuthHandler.class);

        serveCement("/requests").with(RequestsHandler.class);
        serveCement("/request/*").with(RequestHandler.class);
        serveCement("/logs").with(LogsHandler.class);
        serveCement("/log/*").with(LogHandler.class);
        serveCement("/test").with(TestHandler.class);
        serveCement("/social/*").with(SocialHandler.class);
        serveCement("/user-management/*").with(UserManagementHandler.class);

        // response formatting and writers
        setDefaultResponseFormat("json");
    }
}
