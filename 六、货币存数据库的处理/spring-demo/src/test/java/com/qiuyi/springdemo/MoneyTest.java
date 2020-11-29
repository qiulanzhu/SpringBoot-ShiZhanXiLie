package com.qiuyi.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

@Slf4j
class MoneyTest {
    @Test
    void test01(){
        Money m = Money.of(CurrencyUnit.of("CNY"), 20.15);
        log.info(m.toString());
        log.info(m.getAmount().toPlainString());
        log.info(m.getAmountMinor().toString());
        log.info(m.getAmountMinorLong() + "");
        log.info(m.getAmountMajorLong() + "");

        m = Money.of(CurrencyUnit.of("CNY"), 20.1);
        log.info(m.toString());
        log.info(m.getAmount().toPlainString());
        log.info(m.getAmountMinor().toString());
        log.info(m.getAmountMinorLong() + "");
        log.info(m.getAmountMajorLong() + "");
    }
}
