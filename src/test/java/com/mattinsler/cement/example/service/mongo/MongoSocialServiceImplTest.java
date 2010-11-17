package com.mattinsler.cement.example.service.mongo;

import atunit.AtUnit;
import atunit.Container;
import com.google.inject.Inject;
import com.mattinsler.cement.example.service.AbstractSocialServiceContractTest;
import com.mattinsler.cement.example.service.SocialService;
import org.junit.runner.RunWith;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 9, 2010
 * Time: 9:51:42 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(AtUnit.class)
@Container(Container.Option.GUICE)
public class MongoSocialServiceImplTest extends AbstractSocialServiceContractTest {
    @Inject
    MongoSocialServiceImpl impl;

    @Override
    protected SocialService getContract() {
        return impl;
    }
}
