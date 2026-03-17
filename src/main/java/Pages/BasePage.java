package Pages;

import Utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        js = (JavascriptExecutor) getDriver();
        actions = new Actions(getDriver());
    }

    // ---------------- BASIC ACTIONS ---------------- //

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        driver.findElement(locator).sendKeys(text);
    }
    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    public boolean isDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
    public boolean isEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }
    public boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    // ---------------- WAIT METHODS ---------------- //

    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitForElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ---------------- DROPDOWN ---------------- //

    public void selectByVisibleText(By locator, String text) {
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public void selectByValue(By locator, String value) {
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }
    public void selectByIndex(By locator, int index) {
        WebElement element = driver.findElement(locator);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    // ---------------- JAVASCRIPT ---------------- //

    public void jsClick(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    // ---------------- ACTIONS CLASS ---------------- //

    public void hover(By locator) {
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }
    public void doubleClick(By locator) {
        WebElement element = driver.findElement(locator);
        actions.doubleClick(element).perform();
    }
    public void rightClick(By locator) {
        WebElement element = driver.findElement(locator);
        actions.contextClick(element).perform();
    }

    // ---------------- ALERTS ---------------- //

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    // ---------------- FRAMES ---------------- //

    public void switchToFrame(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // ---------------- WINDOW HANDLING ---------------- //

    public void switchToNewWindow() {
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }
    }

    // ---------------- LIST ELEMENTS ---------------- //

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }
    public int getElementsCount(By locator) {
        return driver.findElements(locator).size();
    }

    // ---------------- PAGE METHODS ---------------- //

    public String getPageTitle() {
        return driver.getTitle();
    }
    public String getPageURL() {
        return driver.getCurrentUrl();
    }
    public void navigateTo(String url) {
        driver.get(url);
    }
    public void refreshPage() {
        driver.navigate().refresh();
    }

}
