package com.mattinsler.cement.example.contract.mapper;

import com.mattinsler.contract.ContractMapper;
import com.mattinsler.cement.example.contract.RequestLogContract;
import com.mattinsler.cement.mongo.model.RequestEntity;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/25/10
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestLogMapper extends ContractMapper<RequestEntity, RequestLogContract> {
    protected void mapContract(RequestLogContract contract, RequestEntity value) {
        map(contract.request_id()).to(value.getRequestId());
        map(contract.server_hostname()).to(value.getServerHostname());
        map(contract.request_timestamp()).to(value.getRequestTimestamp());
        map(contract.response_timestamp()).to(value.getResponseTimestamp());

        map(contract.log_count()).to(value.getLogCount());
        map(contract.log_link()).to(new FieldMapper<RequestEntity, String>() {
            public String mapField(RequestEntity value) {
                return "/log/" + value.getRequestId();
            }
        });

        map(contract.source_address()).to(value.getSourceAddress());

        map(contract.request_method()).to(value.getRequestMethod());
        map(contract.request_url()).to(value.getRequestUrl());
        map(contract.request_address()).to(value.getRequestAddress());
        map(contract.request_path()).to(value.getRequestPath());
        map(contract.request_query()).to(value.getRequestQuery());

        map(contract.request_headers()).to(value.getHeaderMap());
        map(contract.request_parameters()).to(value.getParameterMap());
    }
}
