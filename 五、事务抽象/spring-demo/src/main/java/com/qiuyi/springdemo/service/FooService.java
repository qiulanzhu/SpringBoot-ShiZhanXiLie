package com.qiuyi.springdemo.service;

import org.springframework.stereotype.Component;

@Component
public interface FooService {
    public void insertRecord();

    public void insertThenRollback() throws RollbackException;

    public void invokeInsertThenRollback() throws RollbackException;
}
