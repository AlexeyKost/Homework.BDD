package ru.netology.testBank.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.testBank.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransactionPage {

    private SelenideElement transactionButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transactionHead =$(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id=error-message]");

    public TransactionPage() {
        transactionHead.shouldBe(visible);
    }

    public DashboardPage makeValidTransaction(String amountToTransaction, DataHelper.CardInfo cardInfo) {
        makeTransaction(amountToTransaction, cardInfo);
        return new DashboardPage();
    }

    public void makeTransaction(String amountToTransaction, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransaction);
        fromInput.setValue(cardInfo.getCardNumber());
        transactionButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }


}
