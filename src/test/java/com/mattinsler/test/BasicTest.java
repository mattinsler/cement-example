package com.mattinsler.test;

import com.google.inject.*;
import com.lowereast.guiceymongo.guice.GuiceyMongo;
import com.lowereast.guiceymongo.guice.annotation.GuiceyMongoDatabase;
import com.mattinsler.cement.example.guice.Configurations;
import com.mattinsler.cement.example.guice.DatabaseModule;
import com.mattinsler.cement.example.guice.Databases;
import com.mattinsler.cement.example.guice.MongoServiceModule;
import com.mattinsler.cement.util.Function;
import com.mattinsler.cement.util.StringUtil;
import com.mongodb.DB;
import com.mongodb.MongoException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: Oct 6, 2010
 * Time: 1:25:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicTest implements Module {
    public void configure(Binder binder) {
        binder.install(new MongoServiceModule());
    }

    @Inject
    @GuiceyMongoDatabase(Databases.Main)
    DB _database;

    private static Injector _staticInjector;

    @BeforeClass
    public static void beforeClass() {
        _staticInjector = Guice.createInjector(
                new DatabaseModule(),
                GuiceyMongo.chooseConfiguration(Configurations.Test)
        );
    }

    @Before
    public void before() {
        Injector injector = _staticInjector.createChildInjector(this);
        injector.injectMembers(this);
    }

    @After
    public void after() {
        try {
            for (String name : _database.getCollectionNames()) {
                _database.getCollection(name).drop();
            }
        } catch (MongoException e) {
            // ignore, this should be for doing getCollection on a system.* collection
        }
    }

    private static <T> String toString(T value) {
        try {
            Class<?> type = value.getClass();
            Method toStringMethod = type.getMethod("toString");
            if (Object.class.equals(toStringMethod.getDeclaringClass())) {
                StringBuilder builder = new StringBuilder(type.getSimpleName() + " {");
                for (Method method : type.getMethods()) {
                    if (type.equals(method.getDeclaringClass())) {
                        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
                            if (builder.length() > 0) {
                                builder.append(",");
                            }
                            builder.append(method.getName().substring(3) + ":");
                            Object o = method.invoke(value);
                            if (o instanceof String) {
                                builder.append("\"" + o + "\"");
                            } else {
                                builder.append(o);
                            }
                        }
                    }
                }
                builder.append("}");
                return builder.toString();
            }
        } catch (Exception e) {
            // ignore
        }
        return value.toString();
    }

    protected static <T> void appendList(Iterable<T> list, Appendable appendable) {
        try {
            if (list == null) {
                appendable.append("null");
            } else {
                appendable.append("[").append(StringUtil.join(list, ",", new Function<String, T>() {
                    public String execute(T argument) {
                        return BasicTest.toString(argument);
                    }
                })).append("]");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void fail(Iterable<?> expected, Iterable<?> actual) {
        StringBuilder builder = new StringBuilder("expected: ");
        appendList(expected, builder);
        builder.append(" actual: ");
        appendList(actual, builder);
        Assert.fail(builder.toString());
    }

    public interface Comparator<T, U> {
        boolean isEqual(T t, U u);
    }

    protected static <T> void assertIterableEquals(Iterable<T> expected, Iterable<T> actual) {
        assertIterableEquals(expected, actual, new Comparator<T, T>() {
            public boolean isEqual(T e, T a) {
                return e.equals(a);
            }
        });
    }

    protected static <E, A> void assertIterableEquals(Iterable<E> expected, Iterable<A> actual, Comparator<E, A> comparator) {
        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            fail(expected, actual);
        }

        Iterator<E> expectedIterator = expected.iterator();
        Iterator<A> actualIterator = actual.iterator();
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            if (!comparator.isEqual(expectedIterator.next(), actualIterator.next())) {
                fail(expected, actual);
            }
        }
        if (expectedIterator.hasNext() || actualIterator.hasNext()) {
            fail(expected, actual);
        }
    }
}
