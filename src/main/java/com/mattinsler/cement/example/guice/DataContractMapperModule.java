package com.mattinsler.cement.example.guice;

import com.mattinsler.cement.example.contract.mapper.ExceptionMapper;
import com.mattinsler.cement.example.contract.mapper.LogMapper;
import com.mattinsler.contract.json.JsonSerializationWriter;
import com.mattinsler.cement.example.contract.mapper.AddressMapper;
import com.mattinsler.cement.example.contract.mapper.RequestLogMapper;
import com.mattinsler.contract.guice.ContractModule;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/25/10
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataContractMapperModule extends ContractModule {
    @Override
    protected void configure() {
        bindMapper(AddressMapper.class);
        bindMapper(ExceptionMapper.class);
        bindMapper(LogMapper.class);
        bindMapper(RequestLogMapper.class);

        bindWriter(JsonSerializationWriter.class);
    }
}
