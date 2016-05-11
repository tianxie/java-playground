package com.practicalunittesting;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zyq on 16/5/11.
 */
public class MoneyTest {
    @Test
    public void constructorShouldSetAmountAndCurrency() {
        Money money = new Money(10, "USD");

        assertEquals(10, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }
}