package com.mattinsler.cement.example.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.lowereast.guiceymongo.guice.GuiceyMongo;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Sep 23, 2010
 * Time: 2:00:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        try {
        return Guice.createInjector(
                // db setups
                new DatabaseModule(),
                // logging setup
                new LoggerModule(),
                // routing setup
                new RoutingAndResponseModule(),
                // contract mappings
                new DataContractMapperModule(),
                // mongo implementations of services
                new MongoServiceModule(),
                // authentication setup
                new AuthenticatedUserProviderModule(),

                GuiceyMongo.chooseConfiguration(Configurations.Development)
        );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
