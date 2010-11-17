package com.mattinsler.test;

import org.junit.Before;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/16/10
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicContractTest<T> extends BasicTest {
    public T contract;

    protected abstract T getContract();

    @Before
    public void before() {
        contract = getContract();
        super.before();
    }
}
