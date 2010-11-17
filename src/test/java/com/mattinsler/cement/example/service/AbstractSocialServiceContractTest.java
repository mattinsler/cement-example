package com.mattinsler.cement.example.service;

import com.google.inject.Inject;
import com.mattinsler.cement.example.model.SocialEntity;
import com.mattinsler.cement.example.model.UserEntity;
import com.mattinsler.test.BasicContractTest;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 3, 2010
 * Time: 10:46:40 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSocialServiceContractTest extends BasicContractTest<SocialService> {
    @Inject
    UserService userService;
    private UserEntity[] user;

    @Before
    public void before() {
        user = new UserEntity[5];
        for (int x = 0; x < 5; ++x) {
            user[x] = userService.create("user" + x, "password", "name " + x, "email." + x + "@fake.com");
        }
        super.before();
    }

    private static final Comparator<ObjectId, SocialEntity> SocialEntityDestinationComparator = new Comparator<ObjectId, SocialEntity>() {
        public boolean isEqual(ObjectId objectId, SocialEntity socialEntity) {
            return objectId.equals(socialEntity.getDestinationUser());
        }
    };

    private static final Comparator<ObjectId, SocialEntity> SocialEntitySourceComparator = new Comparator<ObjectId, SocialEntity>() {
        public boolean isEqual(ObjectId objectId, SocialEntity socialEntity) {
            return objectId.equals(socialEntity.getSourceUser());
        }
    };

    @Test
    public void testFollow() {
        contract.follow(user[0], user[1]);

        Assert.assertTrue(contract.isFollowing(user[0], user[1]));
        Assert.assertTrue(contract.isFollowedBy(user[1], user[0]));

        assertIterableEquals(Arrays.asList(user[1].getIdentity()), contract.getFollowing(user[0]), SocialEntityDestinationComparator);
        assertIterableEquals(Arrays.asList(user[0].getIdentity()), contract.getFollowers(user[1]), SocialEntitySourceComparator);
    }

    @Test
    public void testUnfollow() {
        contract.follow(user[0], user[1]);
        contract.unfollow(user[0], user[1]);

        Assert.assertFalse(contract.isFollowing(user[0], user[1]));
        Assert.assertFalse(contract.isFollowedBy(user[1], user[0]));

        assertIterableEquals(Collections.EMPTY_LIST, contract.getFollowing(user[0]), SocialEntityDestinationComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getFollowers(user[1]), SocialEntitySourceComparator);
    }

    @Test
    public void testBlock() {
        contract.block(user[0], user[1]);

        Assert.assertTrue(contract.isBlocking(user[0], user[1]));
        Assert.assertTrue(contract.isBlockedBy(user[1], user[0]));

        assertIterableEquals(Arrays.asList(user[1].getIdentity()), contract.getBlocking(user[0]), SocialEntityDestinationComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlocking(user[1]), SocialEntityDestinationComparator);

        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlockedBy(user[0]), SocialEntitySourceComparator);
        assertIterableEquals(Arrays.asList(user[0].getIdentity()), contract.getBlockedBy(user[1]), SocialEntitySourceComparator);
    }

    @Test
    public void testUnblock() {
        contract.block(user[0], user[1]);
        contract.unblock(user[0], user[1]);

        Assert.assertFalse(contract.isBlocking(user[0], user[1]));
        Assert.assertFalse(contract.isBlockedBy(user[1], user[0]));

        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlocking(user[0]), SocialEntityDestinationComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlocking(user[1]), SocialEntityDestinationComparator);

        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlockedBy(user[0]), SocialEntitySourceComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlockedBy(user[1]), SocialEntitySourceComparator);
    }

    @Test
    public void testBlockFollower() {
        contract.follow(user[0], user[1]);
        contract.block(user[1], user[0]);

        Assert.assertFalse(contract.isFollowing(user[1], user[0]));
        Assert.assertFalse(contract.isFollowedBy(user[0], user[1]));
        Assert.assertTrue(contract.isBlocking(user[1], user[0]));
        Assert.assertTrue(contract.isBlockedBy(user[0], user[1]));

        assertIterableEquals(Collections.EMPTY_LIST, contract.getFollowing(user[0]), SocialEntitySourceComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getFollowing(user[1]), SocialEntitySourceComparator);

        assertIterableEquals(Collections.EMPTY_LIST, contract.getFollowers(user[0]), SocialEntitySourceComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getFollowers(user[1]), SocialEntitySourceComparator);

        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlocking(user[0]), SocialEntityDestinationComparator);
        assertIterableEquals(Arrays.asList(user[0].getIdentity()), contract.getBlocking(user[1]), SocialEntityDestinationComparator);

        assertIterableEquals(Arrays.asList(user[1].getIdentity()), contract.getBlockedBy(user[0]), SocialEntitySourceComparator);
        assertIterableEquals(Collections.EMPTY_LIST, contract.getBlockedBy(user[1]), SocialEntitySourceComparator);
    }

    @Test(expected = RuntimeException.class)
    public void testFollowWhenBlocked() {
        contract.block(user[0], user[1]);
        contract.follow(user[1], user[0]);
    }
}
