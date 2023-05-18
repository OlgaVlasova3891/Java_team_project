package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {
    // 1. Тестирование валидных значений нового обьекта CreditAccount
    @Test

    public void shouldGetCreditLimit() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(5_000, account.getCreditLimit());
    }

    @Test
    public void shouldGetBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldGetRate() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(15, account.getRate());
    }

    // 2. Тестирование функциональности обьекта при изменении процентной ставки (rate)
    @Test
    public void testChangeRate() {
        // rate > 0; Изменение процентной ставки
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.setRate(10);
        Assertions.assertEquals(10, account.getRate());
    }
    //@Test
    //public void testNegativeRate() {
    // rate < 0; Изменения процентной ставки
    // CreditAccount account = new CreditAccount(0, 5_000, 15);
    // account.setRate(-10);
    // Assertions.assertEquals(15, account.getRate());
    // }

    //@Test
    // public void TestZeroRate() {
    //    CreditAccount account = new CreditAccount(0, 5_000, 15);
    //  account.setRate(0);
    //   Assertions.assertEquals(15, account.getRate());
    //}

    // 3. Тестирование функциональности обьекта по пополнению кредитного счёта (CreditAccount)
    // 3.1 initialBalance = 0; amount > 0
    @Test
    public void shouldAddToZeroInitialBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000); // добавляем положительную сумму на 0 баланс счёта

        Assertions.assertEquals(3_000, account.getBalance());
    }

    //@Test
    //public void shouldAddToNegativeBalance() {
    // CreditAccount account = new CreditAccount(
    //        0,
    //          5_000,
    //            15
    //   );

    //   account.add(-3_000); // добавляем отрицательную сумму на 0 баланс счёта

    //   Assertions.assertEquals(0, account.getBalance());
    // }

    //@Test
    //public void shouldAddToZeroBalance() {
    //    CreditAccount account = new CreditAccount(
    //           0,
    //           5_000,
    //           15
    //   );

    //    account.add(0);// добавляем нулевую сумму на 0 баланс счёта

    //   Assertions.assertEquals(0, account.getBalance());
    //}

    // 3.2 initialBalance > 0; amount > 0
    @Test
    public void shouldAddPositiveAmountToPositiveInitialBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(4_000); // добавляем положительную сумму на положительный баланс счёта

        Assertions.assertEquals(5_000, account.getBalance());
    }

    // 3.3 initialBalance > 0; amount = 0
    @Test
    public void shouldAddZeroAmountToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(0); // добавляем сумму = 0 на положительный баланс счёта

        Assertions.assertEquals(1_000, account.getBalance());
    }

    // 3.4 initialBalance > 0; amount < 0
    @Test
    public void shouldAddNegativeAmountToPositiveInitialBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-3_000); // добавляем отрицательную сумму на положительный баланс счёта

        Assertions.assertEquals(1_000, account.getBalance());
    }

    // 3.5 amount > 0; initialBalance < 0
    @Test
    public void shouldAddPositiveAmountToNegativeInitialBalance() {
        CreditAccount account = new CreditAccount(
                -1_000,
                5_000,
                15
        );

        account.add(3_000); // добавляем положительную сумму на отрицательный баланс счёта

        Assertions.assertEquals(2_000, account.getBalance());
    }

    // @Test
    // public void testAddNegativeAmountToNegativeBalance() {
    //     CreditAccount account = new CreditAccount(
    //            -1_000,
    //           5_000,
    //            15
    //     );

    //    account.add(-3_000); // добавляем отрицательную сумму на отрицательный баланс счёта
    //   Assertions.assertEquals(-1_000, account.getBalance());
    //}

    //@Test
    //public void testAddZeroAmountToNegativeBalance() {
    //    CreditAccount account = new CreditAccount(
    //            -1_000,
    //            5_000,
    //            15
    //   );
    //   account.add(0); // добавляем сумму = 0 на отрицательный баланс счёта
    //    Assertions.assertEquals(-1_000, account.getBalance());
    //}

    // 4. Операция расчета процентов
    @Test
    public void shouldGetYearChangeToNegativeBalance() {
        // initialBalance < 0; rate > 0
        CreditAccount account = new CreditAccount(-1_000, 5_000, 15);
        Assertions.assertEquals(-150, account.yearChange());
    }

    @Test
    public void shouldGetYearChangeToZeroBalance() {
        // initialBalance = 0; rate > 0
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldGetYearChangeToPositiveBalance() {
        // initialBalance > 0; rate > 0
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }

    // 5. Тестирование функционала на осуществление покупки
    @Test
    // amount < 0
    public void testAmountPayBelowZero() {
        Account account = new CreditAccount(5_000, 5_000, 15);
        assertFalse(account.pay(-1_000)); // опреция не должна осуществиться
        assertEquals(5_000, account.getBalance());
    }

    // amount = 0
    @Test
    public void testZeroAmountPayAboveZero() {
        Account account = new CreditAccount(5_000, 5_000, 15);
        assertFalse(account.pay(0)); // опреция не должна осуществиться
        assertEquals(5_000, account.getBalance());
    }

    // amount > 0
    @Test
    public void testAmountPayAboveZero() {
        Account account = new CreditAccount(5_000, 5_000, 15);
        assertTrue(account.pay(1_000)); // опреция должна осуществиться
        assertEquals(4_000, account.getBalance());
    }


    //@Test
    //public void shouldCalculateBalanceAfterPayment() {
    //    Account account = new CreditAccount(4_000, 5_000, 15);
    //    assertTrue(account.pay(2_000));
    //   assertEquals(2_000, account.getBalance());
    //}


    // @Test
    // public void testPayIfBalanceBelowNegativeCreditLimit() {
    //   Account account = new CreditAccount(4_000, 5_000, 15);
    //  assertTrue(account.pay(7_000));
    //  assertEquals(-_000, account.getBalance());
    //}

    // Покупка на сумму равную нулю при отрицательном кредитном лимите.
    //@Test
    //public void testZeroPayIfBalanceBelowNegativeCreditLimit() {
    //  Account account = new CreditAccount(0, -5_000, 15);
    // assertTrue(account.pay(2_000));
    // assertEquals(-2_000, account.getBalance());
    // }

    // Покупка на сумму меньше отрицательного кредитного лимита
    // Баг 9. Операция не должна быть выполнена
    //@Test
    //public void testNegativePayIfBalanceBelowNegativeCreditLimit() {
    //   Account account = new CreditAccount(-6_000, -5_000, 15);
    //  assertFalse(account.pay(2_000));
    //  assertEquals(-6_000, account.getBalance());

    //}

    //amount > creditLimit
    @Test
    public void testPayAboveCreditLimit() {
        Account account = new CreditAccount(3_000, 5_000, 15);
        assertTrue(account.pay(9000));
        assertEquals(-2_000, account.getBalance());
    }

    // 5.1 Тестирование функциональности - появление исключения IllegalArgumentException при отрицательной процентной ставки (rate).
    @Test
    public void testExceptionIfNegativeRate() {
        CreditAccount account = new CreditAccount(300, 5_000, -15);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.yearChange();
        });
    }
    // 5.2 Тестирование функциональности
    //  появление исключения IllegalArgumentException при отрицательном балансе кредитного счёта.
    //@Test
    //public void testExceptionIfNegativeBalance() {
    //   CreditAccount account = new CreditAccount(-300, 5_000, 15);
    //  Assertions.assertThrows(IllegalArgumentException.class, () -> {
    //      account.yearChange();
    // });
    // }

    // 5.2 Тестирование функциональности -
    // появление исключения IllegalArgumentException при отрицательном кредитном лимите.
    @Test
    public void testExceptionIfNegativeCreditLimit() {
        CreditAccount account = new CreditAccount(300, -5_000, 15);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(100);
        });
    }
}



