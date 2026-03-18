package Pages;

import Utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends DriverFactory {
    //  protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    protected Actions actions;

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        js = (JavascriptExecutor) getDriver();
        actions = new Actions(getDriver());
    }

    // ================= BASIC ACTIONS ================= //

    public void click(WebElement element) {
        retryAction(() -> {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        });
    }

    public void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public String getAttribute(WebElement element, String attr) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(attr);
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isSelected(WebElement element) {
        return element.isSelected();
    }

    // ================= WAIT METHODS ================= //

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForText(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForPageLoad() {
        wait.until(driver ->
                ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public void waitForUrlContains(String partialUrl) {
        wait.until(ExpectedConditions.urlContains(partialUrl));
    }

    // ================= RETRY WRAPPER ================= //

    private void retryAction(Runnable action) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                action.run();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                attempts++;
                sleep(500);
            }
        }
        throw new RuntimeException("Action failed after retries");
    }

    // ================= JAVASCRIPT ================= //

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0)");
    }

    public void setValueUsingJS(WebElement element, String value) {
        js.executeScript("arguments[0].value='" + value + "';", element);
    }

    // ================= DROPDOWN ================= //

    public void selectByText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public List<WebElement> getAllOptions(WebElement element) {
        return new Select(element).getOptions();
    }

    // ================= ACTIONS ================= //

    public void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    // ================= ALERT ================= //

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void sendAlertText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // ================= FRAME ================= //

    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // ================= WINDOWS ================= //

    public void switchToNewWindow() {
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }
    }

    // ================= LIST ================= //

    public int getSize(List<WebElement> elements) {
        return elements.size();
    }

    public WebElement getElementByIndex(List<WebElement> elements, int index) {
        return elements.get(index);
    }

    // ================= PAGE ================= //

    public String getTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void back() {
        driver.navigate().back();
    }

    public void forward() {
        driver.navigate().forward();
    }

    // ================= UTIL ================= //

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}