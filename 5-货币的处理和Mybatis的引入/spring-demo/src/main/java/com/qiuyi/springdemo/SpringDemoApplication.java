package com.qiuyi.springdemo;

import com.qiuyi.springdemo.mapper.CoffeeMapper;
import com.qiuyi.springdemo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
@MapperScan("com.qiuyi.springdemo.mapper")
public class SpringDemoApplication implements ApplicationRunner {
    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee c = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        int count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = coffeeMapper.findById(1L);
        log.info("Find Coffe: {}", c);

    }
}
