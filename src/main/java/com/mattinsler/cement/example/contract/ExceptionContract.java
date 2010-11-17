package com.mattinsler.cement.example.contract;

import com.mattinsler.contract.IsContract;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 11/4/10
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ExceptionContract extends IsContract {
    public interface StackElementContract extends IsContract {
        String filename();
        String method();
        int line();
        String class_name();
    }

    public interface ThrowableContract extends IsContract {
        String message();
        String class_name();
        List<StackElementContract> stack();
    }

    List<ThrowableContract> throwables();
}
