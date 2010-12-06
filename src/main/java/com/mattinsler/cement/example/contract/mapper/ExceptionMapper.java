package com.mattinsler.cement.example.contract.mapper;

import com.mattinsler.cement.example.contract.ExceptionContract;
import com.mattinsler.cement.mongo.model.ExceptionEntity;
import com.mattinsler.contract.ContractMapper;

/**
 * Created by IntelliJ IDEA.
 * User: mattinsler
 * Date: 11/4/10
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionMapper extends ContractMapper<ExceptionEntity, ExceptionContract> {
    protected void mapContract(ExceptionContract contract, ExceptionEntity value) {
        map(contract.throwables()).to(value.getThrowableList());
    }

    public static class ThrowableMapper extends ContractMapper<ExceptionEntity.Throwable, ExceptionContract.ThrowableContract> {
        protected void mapContract(ExceptionContract.ThrowableContract contract, ExceptionEntity.Throwable value) {
            map(contract.message()).to(value.getMessage());
            map(contract.class_name()).to(value.getFullClassName());
            map(contract.stack()).to(value.getStackElementList());
        }
    }

    public static class StackElementMapper extends ContractMapper<ExceptionEntity.StackElement, ExceptionContract.StackElementContract> {
        protected void mapContract(ExceptionContract.StackElementContract contract, ExceptionEntity.StackElement value) {
            map(contract.filename()).to(value.getFilename());
            map(contract.class_name()).to(value.getFilename());
            map(contract.line()).to(value.getLine());
            map(contract.method()).to(value.getMethod());
        }
    }
}
