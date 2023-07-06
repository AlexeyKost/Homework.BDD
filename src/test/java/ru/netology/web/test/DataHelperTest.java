package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.DashboardPage;

import static ru.netology.web.page.DashboardPage.*;
import static ru.netology.web.data.DataHelper.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class DataHelperTest {
    private int[] cardsBalance;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        new LoginPage().validLogin(getAuthInfo()).validVerify(getVerificationCodeFor(getAuthInfo()));
        cardsBalance = cardsBalance();
        cardsBalance = justifyBalance(cardsBalance[1], cardsBalance[2]);
        assertEquals(cardsBalance[1], cardsBalance[2]);
    }

    @AfterEach
    void asserting() {
        cardsBalance = cardsBalance();
        cardsBalance = justifyBalance(cardsBalance[1], cardsBalance[2]);
        assertEquals(cardsBalance[1], cardsBalance[2]);
    }

    @Test
    void shouldDefineCardsBalance() {
        assertEquals(10_000, cardsBalance[1]);
        assertEquals(10_000, cardsBalance[2]);
    }

    @Test
    void shouldJustifyIfFirstSurpass() {
        new DashboardPage().moneyTransfer(cardNumber(1)).transaction("5 637", cardNumber(2));
    }

    @Test
    void shouldJustifyIfSecondSurpass() {
        new DashboardPage().moneyTransfer(cardNumber(2)).transaction("5 637", cardNumber(1));
    }
}