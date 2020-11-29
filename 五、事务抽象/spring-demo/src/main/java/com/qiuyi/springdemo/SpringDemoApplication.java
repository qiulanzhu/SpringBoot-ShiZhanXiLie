package com.qiuyi.springdemo;

import com.qiuyi.springdemo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@Slf4j
public class SpringDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FooService fooService;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        run_programmaric_transaction();
        run_declarative_transaction();
    }

    private long getCount() {
        return (long) jdbcTemplate
                .queryForList("select count(1) cnt from foo")
                .get(0)
                .get("cnt");
    }

    private void run_programmaric_transaction() {
        log.info("count before transaction: {}", getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("insert into foo(value) values('ccc')");
                log.info("count doing transaction: {}", getCount());
                transactionStatus.setRollbackOnly();
            }
        });

        log.info("count after transaction: {}", getCount());
    }

    private void run_declarative_transaction() {
        fooService.insertRecord();
        log.info("normal transaction: {}", getCount());

        try {
            fooService.insertThenRollback();
        }catch (Exception e){
            log.info("exception transaction 1: {}", getCount());
        }

        try {
            fooService.invokeInsertThenRollback();
        }catch (Exception e){
            log.info("exception transaction 2: {}", getCount());
        }
    }
}
