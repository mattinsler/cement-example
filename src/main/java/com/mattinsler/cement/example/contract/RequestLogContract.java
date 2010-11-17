package com.mattinsler.cement.example.contract;

import com.mattinsler.contract.IsContract;

import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/25/10
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RequestLogContract extends IsContract {
    String request_id();
    String server_hostname();
    Date request_timestamp();
    Date response_timestamp();

    int log_count();
    String log_link();

    AddressContract source_address();

    String request_method();
    String request_url();
    AddressContract request_address();
    String request_path();
    String request_query();
    Map<String, String> request_headers();
    Map<String, String> request_parameters();
}
