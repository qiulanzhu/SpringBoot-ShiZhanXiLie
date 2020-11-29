package com.qiuyi.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FooServiceImpl implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo(value) values('ddd')");
    }

    @Override
    @Transactional(rollbackFor=RollbackException.class)
    public void insertThenRollback() throws RollbackException{
        jdbcTemplate.execute("insert into foo(value) values('eee')");

        // 触发事务回滚
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        // 对象内部调用不触发事务回滚
        insertThenRollback();
    }
}
