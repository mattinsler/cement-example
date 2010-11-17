package com.mattinsler.cement.example.service.mongo;

import com.google.inject.Inject;
import com.lowereast.guiceymongo.GuiceyCollection;
import com.lowereast.guiceymongo.guice.annotation.GuiceyMongoCollection;
import com.mattinsler.cement.example.guice.Collections;
import com.mattinsler.cement.example.model.OAuthAccessEntity;
import com.mattinsler.cement.example.model.OAuthConsumerEntity;
import com.mattinsler.cement.example.service.OAuthService;
import com.mongodb.BasicDBObject;
import net.oauth.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 12, 2010
 * Time: 1:30:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class MongoOAuthServiceImpl implements OAuthService {
    private final GuiceyCollection<OAuthAccessEntity> _accessCollection;
    private final GuiceyCollection<OAuthConsumerEntity> _consumerCollection;

    @Inject
    MongoOAuthServiceImpl(@GuiceyMongoCollection(Collections.OAuthAccess) GuiceyCollection<OAuthAccessEntity> accessCollection,
                          @GuiceyMongoCollection(Collections.OAuthConsumer) GuiceyCollection<OAuthConsumerEntity> consumerCollection) {
        _accessCollection = accessCollection;
        _consumerCollection = consumerCollection;
    }

    public OAuthConsumer createConsumer(String author, String description, String callback) {
        // do something about limiting by author or something ...

        String key = DigestUtils.md5Hex(author + description + callback + System.nanoTime());
        String secret = DigestUtils.md5Hex(key + System.nanoTime());
        _consumerCollection.save(OAuthConsumerEntity.newBuilder()
                .setKey(key)
                .setSecret(secret)
                .setAuthor(author)
                .setDescription(description)
                .setCallback(callback)
        );

        OAuthConsumer consumer = new OAuthConsumer(callback, key, secret, null);
        consumer.setProperty("name", author);
        consumer.setProperty("description", description);
        return consumer;
    }

    public OAuthConsumer getConsumer(OAuthMessage message) throws IOException, OAuthProblemException {
        OAuthConsumerEntity entity = _consumerCollection.findOne(OAuthConsumerEntity.newBuilder()
                .setKey(message.getConsumerKey())
                .build()
        );
        if (entity == null) {
            throw new OAuthProblemException("token_rejected");
        }
        OAuthConsumer consumer = new OAuthConsumer(entity.getCallback(), entity.getKey(), entity.getSecret(), null);
        consumer.setProperty("name", entity.getAuthor());
        consumer.setProperty("description", entity.getDescription());
        return consumer;
    }

    public void generateRequestToken(OAuthAccessor accessor) throws OAuthException {
        accessor.requestToken = DigestUtils.md5Hex(accessor.consumer.consumerKey + System.nanoTime());
        accessor.tokenSecret = DigestUtils.md5Hex(accessor.consumer.consumerKey + System.nanoTime() + accessor.requestToken);
        accessor.accessToken = null;
    }

    public OAuthAccessor getAccessor(OAuthMessage message) throws IOException, OAuthProblemException {
        message.getToken();

        OAuthAccessEntity entity = _accessCollection.findOne(OAuthAccessEntity.newBuilder().build());
        if (entity == null) {
            throw new OAuthProblemException("token_expired");
        }
        return null;
        

//                // try to load from local cache if not throw exception
//        String consumer_token = requestMessage.getToken();
//        OAuthAccessor accessor = null;
//        for (OAuthAccessor a : SampleOAuthProvider.ALL_TOKENS) {
//            if(a.requestToken != null) {
//                if (a.requestToken.equals(consumer_token)) {
//                    accessor = a;
//                    break;
//                }
//            } else if(a.accessToken != null){
//                if (a.accessToken.equals(consumer_token)) {
//                    accessor = a;
//                    break;
//                }
//            }
//        }
//
//        if(accessor == null){
//            OAuthProblemException problem = new OAuthProblemException("token_expired");
//            throw problem;
//        }
//
//        return accessor;
    }

    public void generateAccessToken(OAuthAccessor accessor) throws OAuthException {
        String token = DigestUtils.md5Hex(accessor.consumer.consumerKey + System.nanoTime());

//        _accessCollection.update(
//                OAuthAccessEntity.newBuilder().setToken(accessor.accessToken).build(),
//                new BasicDBObject("$set",
//                        new BasicDBObject(OAuthAccessEntity., new ObjectId(userId))
//                        .append(OAuthAccessEntity.AuthorizedKey, true)
//                ),
//                true,
//                false
//        );

//        // first remove the accessor from cache
//        ALL_TOKENS.remove(accessor);
//
//        accessor.requestToken = null;
//        accessor.accessToken = token;
//
//        // update token in local cache
//        ALL_TOKENS.add(accessor);
    }

    public void markAsAuthorized(OAuthAccessor accessor, String userId) throws OAuthException {
        _accessCollection.update(
                OAuthAccessEntity.newBuilder().setToken(accessor.accessToken).build(),
                new BasicDBObject("$set",
                        new BasicDBObject(OAuthAccessEntity.UserKey, new ObjectId(userId))
                        .append(OAuthAccessEntity.AuthorizedKey, true)
                ),
                true,
                false
        );

        accessor.setProperty("user", userId);
        accessor.setProperty("authorized", Boolean.TRUE);
    }
}
