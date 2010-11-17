package com.mattinsler.cement.example.contract;

import com.mattinsler.contract.IsContract;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/16/10
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AddressContract extends IsContract {
    String address();
    int port();
}
