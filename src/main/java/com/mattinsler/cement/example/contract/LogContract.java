package com.mattinsler.cement.example.contract;

import com.mattinsler.contract.IsContract;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 11/4/10
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LogContract extends IsContract {
    String request_id();
    String server_hostname();
    Date timestamp();

    String level();
    String message();
    ExceptionContract exception();
}
