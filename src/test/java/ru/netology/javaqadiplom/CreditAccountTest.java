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
    public void TestPositiveRate() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.setRate(10);
        Assertions.assertEquals(10, account.getRate());
    }

    //Баг 1. Процентная ставка не может быть отрицательной
    @Test
    public void TestNegativeRate() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.setRate(-10);
        Assertions.assertEquals(15, account.getRate());
    }
// Баг 2. Процентная ставка не может быть равной нулю
    @Test
    public void TestZeroRate() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        account.setRate(0);
        Assertions.assertEquals(15, account.getRate());
    }

    // 3. Тестирование функциональности обьекта по пополнению кредитного счёта (CreditAccount)
    // 3.1 initialBalance = 0
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000); // добавляем положительную сумму на 0 баланс счёта

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(-3_000); // добавляем отрицательную сумму на 0 баланс счёта

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(0);// добавляем нулевую сумму на 0 баланс счёта

        Assertions.assertEquals(0, account.getBalance());
    }

    // 3.2 initialBalance  положительный
    //Баг 3. При пополнении счёта числа должны сумироваться
    @Test
    public void shouldAddPositiveAmountToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(4_000); // добавляем положительную сумму на положительный баланс счёта

        Assertions.assertEquals(5_000, account.getBalance());
    }

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

    @Test
    public void shouldNegativeAmountToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-3_000); // добавляем отрицательную сумму на положительный баланс счёта

        Assertions.assertEquals(1_000, account.getBalance());
    }

    // 3.3 Добавление суммы на счёт с отрицательным балансом
    //Баг 4. При пополнении счёта числа должны сумироваться
    @Test
    public void shouldPositiveAmountToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -1_000,
                5_000,
                15
        );

        account.add(3_000); // добавляем положительную сумму на отрицательный баланс счёта

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void testAddNegativeAmountToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -1_000,
                5_000,
                15
        );

        account.add(-3_000); // добавляем отрицательную сумму на отрицательный баланс счёта
        Assertions.assertEquals(-1_000, account.getBalance());
    }

    @Test
    public void testAddZeroAmountToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -1_000,
                5_000,
                15
        );
        account.add(0); // добавляем сумму = 0 на отрицательный баланс счёта
        Assertions.assertEquals(-1_000, account.getBalance());
    }

    // 4. Тестирование расчета процентов на отрицательный баланс счёта
    @Test
    public void shouldGetYearChangeToNegativeBalance() {
        // initialBalance = -1_000;
        CreditAccount account = new CreditAccount(-1_000, 5_000, 15);
        Assertions.assertEquals(-150, account.yearChange());
    }

    @Test
    public void shouldGetYearChangeToZeroBalance() {
        // initialBalance = 0;
        CreditAccount account = new CreditAccount(0, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }

    // Баг 5. Проценты расчитываются только на отрицательный баланс
    @Test
    public void shouldGetYearChangeToPositiveBalance() {
        // initialBalance = 1_000;
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }

    // 5. Тестирование функционала на осуществление покупки
    @Test
    // покупка на отрицательную сумму
    public void testPayByAmountBelowZero() {
        Account account = new CreditAccount(5_000, 5_000, 15);
        assertFalse(account.pay(-1_000)); // опреция не должна осуществиться
        assertEquals(5_000, account.getBalance());
    }

    //покупка на сумму равную нулю
    @Test
    public void testPayByZeroAmount() {
        Account account = new CreditAccount(5_000, 5_000, 15);
        assertFalse(account.pay(0)); // опреция не должна осуществиться
        assertEquals(5_000, account.getBalance());
    }

    // Остаток на балансе при совершении покупки
    // Баг 6. После покупки баланс должен быть пересчитан
    // (вычесть сумму покупки из исходной суммы баланса счёта)
    @Test
    public void shouldCalculateBalanceAfterPayment() {
        Account account = new CreditAccount(4_000, 5_000, 15);
        assertTrue(account.pay(2_000));
        assertEquals(2_000, account.getBalance());
    }

    //Осуществление покупки если баланс счёта  больше отрицательного кредитного лимита.
    // Баг 7. Операция должна выполнена
    @Test
    public void testPayIfBalanceBelowNegativeCreditLimit() {
        Account account = new CreditAccount(4_000, -5_000, 15);
        assertTrue(account.pay(7_000));
        assertEquals(1_000, account.getBalance());
    }

    // Покупка на сумму равную нулю при отрицательном кредитном лимите.
    // Баг 8. Операция должна быть выполнена
    @Test
    public void testZeroPayIfBalanceBelowNegativeCreditLimit() {
        Account account = new CreditAccount(0, -5_000, 15);
        assertTrue(account.pay(2_000));
        assertEquals(-2_000, account.getBalance());
    }

    // Покупка на сумму меньше отрицательного кредитного лимита
    // Баг 9. Операция не должна быть выполнена
    @Test
    public void testNegativePayIfBalanceBelowNegativeCreditLimit() {
        Account account = new CreditAccount(-6_000, -5_000, 15);
        assertFalse(account.pay(2_000));
        assertEquals(-6_000, account.getBalance());

    }

    // Покупка на сумму больше положительного кредитного лимита
    @Test
    public void testPayAboveCreditLimit() {
        Account account = new CreditAccount(3_000, 5_000, 15);

        boolean expected = false;
        boolean actual = account.pay(8_010);
        Assertions.assertEquals(expected, actual);
    }

    // 5.1 Тестирование функциональности - появление исключения IllegalArgumentException при отрицательной процентной ставки (rate).
    // Баг 10. Тест провален
    @Test
    public void testExceptionIfNegativeRate() {
        CreditAccount account = new CreditAccount(300, 5_000, -15);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.yearChange();
        });
    }
    // 5.2 Тестирование функциональности - появление исключения IllegalArgumentException при отрицательном балансе кредитного счёта.
    // Баг 11. Тест провален

    @Test
    public void testExceptionIfNegativeBalance() {
        CreditAccount account = new CreditAccount(-300, 5_000, 15);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.yearChange();
        });
    }

    // 5.3 Тестирование функциональности - появление исключения IllegalArgumentException при отрицательном кредитном лимите.
    // Баг 12. Тест провален
    @Test
    public void testExceptionIfNegativeCreditLimit() {
        CreditAccount account = new CreditAccount(300, -5_000, 15);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            account.pay(100);
        });
    }
}



