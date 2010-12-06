package com.mattinsler.cement.example.contract.mapper;

import com.mattinsler.contract.ContractMapper;
import com.mattinsler.cement.example.contract.AddressContract;
import com.mattinsler.cement.mongo.model.RequestEntity;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 10/25/10
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddressMapper extends ContractMapper<RequestEntity.Address, AddressContract> {
    protected void mapContract(AddressContract contract, RequestEntity.Address value) {
        map(contract.address()).to(value.getAddress());
        map(contract.port()).to(value.getPort());
    }
}
