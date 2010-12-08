package com.mattinsler.cement.example.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.mattinsler.cement.example.model.*;
import com.mattinsler.cement.mongo.guice.CementMongo;
import com.mattinsler.guiceymongo.guice.GuiceyMongo;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 3:07:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseModule implements Module {
    public void configure(Binder binder) {
        binder.install(
                GuiceyMongo.configure(Configurations.Test)
                    .cloneFrom(Configurations.Development)
//                    .mapDatabase(Databases.Main).asTestDatabase()
//                    .mapCollection(Collections.User).ofType(UserEntity.class).to("user").inDatabase(Databases.Main)
//                    .mapCollection(Collections.Session).ofType(SessionEntity.class).to("session").inDatabase(Databases.Main)
        );

        binder.install(
                GuiceyMongo.configure(Configurations.Development)
                    .mapDatabase(Databases.Main).to("cement-example-dev")
                    .mapCollection(Collections.OAuthAccess).ofType(OAuthAccessEntity.class).to("oauth-access").inDatabase(Databases.Main)
                    .mapCollection(Collections.OAuthConsumer).ofType(OAuthConsumerEntity.class).to("oauth-consumer").inDatabase(Databases.Main)
                    .mapCollection(Collections.Social).ofType(SocialEntity.class).to("social").inDatabase(Databases.Main)
                    .mapCollection(Collections.Session).ofType(SessionEntity.class).to("session").inDatabase(Databases.Main)
                    .mapCollection(Collections.User).ofType(UserEntity.class).to("user").inDatabase(Databases.Main)
        );

        binder.install(
                CementMongo.configure(Configurations.Development)
                    .mapLogCollection("log").into(Databases.Main)
                    .mapRequestCollection("request").into(Databases.Main)
        );
    }
}
