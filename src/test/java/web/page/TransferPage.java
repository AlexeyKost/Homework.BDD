package web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $("#root > div > h1");
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement from = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("#root > div > form > button.button.button_view_extra.button_size_s.button_theme_alfa-on-white");
    private SelenideElement cancelButton = $("#root > div > form > button:nth-child(3)");

    public TransferPage() {
        heading.shouldBe(visible);
    }

    public void addMoneyToCard1(DataHelper.TransferInfo data) {
        amount.setValue(data.getAmount());
        from.setValue(data.getNumber2());
        transferButton.click();
    }

    public void addMoneyToCard2(DataHelper.TransferInfo data) {
        amount.setValue(data.getAmount());
        from.setValue(data.getNumber1());
        transferButton.click();
    }

    public void cleanUp() {
        amount.doubleClick();
        amount.sendKeys(Keys.DELETE);
        amount.doubleClick();
        amount.sendKeys(Keys.DELETE);
        from.doubleClick();
        from.sendKeys(Keys.DELETE);
        from.doubleClick();
        from.sendKeys(Keys.DELETE);
        from.doubleClick();
        from.sendKeys(Keys.DELETE);
        from.doubleClick();
        from.sendKeys(Keys.DELETE);
        cancelButton.click();
    }
}
