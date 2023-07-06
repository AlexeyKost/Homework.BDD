package web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

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
