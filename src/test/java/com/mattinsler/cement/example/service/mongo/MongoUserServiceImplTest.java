package com.mattinsler.cement.example.service.mongo;

import atunit.AtUnit;
import atunit.Container;
import com.google.inject.Inject;
import com.mattinsler.cement.example.service.AbstractUserServiceContractTest;
import com.mattinsler.cement.example.service.UserService;
import org.junit.runner.RunWith;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/16/10
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(AtUnit.class)
@Container(Container.Option.GUICE)
public class MongoUserServiceImplTest extends AbstractUserServiceContractTest {
    @Inject
    MongoUserServiceImpl impl;

    @Override
    protected UserService getContract() {
        return impl;
    }
}
