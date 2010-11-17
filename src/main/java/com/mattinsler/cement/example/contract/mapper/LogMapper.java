package com.mattinsler.cement.example.contract.mapper;

import com.mattinsler.cement.example.contract.LogContract;
import com.mattinsler.cement.mongo.model.LogEntity;
import com.mattinsler.contract.ContractMapper;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 11/4/10
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogMapper extends ContractMapper<LogContract, LogEntity> {
    protected void mapContract(LogContract contract, LogEntity value) {
        map(contract.request_id()).to(value.getRequestId());
        map(contract.server_hostname()).to(value.getServerHostname());
        map(contract.timestamp()).to(value.getTimestamp());

        map(contract.level()).to(value.getLevel());
        map(contract.message()).to(value.getMessage());
        map(contract.exception()).to(value.getException());
    }
}
