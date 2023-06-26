package pageobjects.common;

import automation.library.common.Property;
import automation.library.common.TestContext;
import automation.library.selenium.core.Element;
import automation.library.selenium.exec.Constants;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import utils.BasePO;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

public class BasePage extends BasePO {
	private By mainBody = By.tagName("main");
	private URIBuilder uri = null;

	/**
	 *
	 * @param by locator of the element
	 * @return true of element is present otherwise false.
	 */
	public boolean isElementPresent(By by) {
		try {
			getWait().until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		} catch (Exception ex) {
			log.debug("Not on checkout page. Could not find checkout headeer", ex);
		}
		return false;
	}

	/**
	 * Will navigate to MyDyson url
	 * @param url MyDyson URL
	 */
	public void navigateToPage(String url) {
		gotoURL(url);
		waitPageToLoad();
	}

	/**
	 * Get page title
	 *
	 * @return page title
	 */
	public String getCurrURL() {
		waitPageToLoad();
		return getDriver().getCurrentUrl();
	}

	/**
	 * Maximize the browsers window.
	 */
	public void maximizeViewPort() {
		getDriver().manage().window().maximize();
	}

	/**
	 * Checks for 404
	 *
	 * @return true if 404
	 */
	public boolean isNotFound() {
		return getTitle().contains("404");
	}

	/**
	 * Checks if there is content in the main tag
	 *
	 * @return true if there is content on the page, false otherwise
	 */
	public boolean hasContent() throws InterruptedException {
		waitPageToLoad();
		int defaultWait = Property.getProperties(TestContext.getInstance().testdataGet("paths").toString()).getInt("defaultWait");
		Thread.sleep(defaultWait * 1000);
		return !findElement(mainBody).getText().isEmpty();
	}

	/**
	 * Get page title
	 *
	 * @return page title
	 */
	public String getTitle() {
		waitPageToLoad();
		return getDriver().getTitle();
	}

	/**
	 * finds an element with text
	 *
	 * @param locator
	 * @param text    search text
	 * @return element if found
	 */
	public Element findElementWithText(By locator, String text) throws Exception {
		List<Element> elements = findElements(locator, -1);

		// if string is a special charater will not replace it with empty space '  '.
		if(text.matches(".*[a-zA-Z].*")){
			text = text.replaceAll("[^a-zA-Z0-9]", " ");
		}

		for (Element element : elements) {
			log.debug("submenu:" + element.getText());
			if (StringUtils.containsIgnoreCase(element.getText(), text) || StringUtils.containsIgnoreCase(element.getAttribute("innerText"), text)) {
				return element;
			}
		}
		String msg = String.format("element with \"%s\" not found", text);
		throw new Exception(msg);
	}

	/**
	 * finds an element with text
	 *
	 * @param locator
	 * @param text    search text
	 * @return element if found
	 */
	public boolean isElementWithTextWithSpecialCharFound(By locator, String text) {
		List<Element> elements = findElements(locator, -1);

		try{
			for (Element element : elements) {
				if (StringUtils.containsIgnoreCase(element.getText(), text) || StringUtils.containsIgnoreCase(element.getAttribute("innerText"), text)) {
					return true;
				}
			}
		}catch(Exception e){
			log.debug("Element with text is not found");
		}
		return false;
	}

	/**
	 * finds an element with text
	 *
	 * @param locator
	 * @param text    search text
	 * @return element if found
	 */
	public Element findElementWithSpecialChar(By locator, String text) throws Exception {
		List<Element> elements = findElements(locator, -1);

		for (Element element : elements) {
			if (StringUtils.containsIgnoreCase(element.getText(), text) || StringUtils.containsIgnoreCase(element.getAttribute("innerText"), text)) {
				return element;
			}
		}
		String msg = String.format("element with \"%s\" not found", text);
		throw new Exception(msg);
	}

	/**
	 * checks a element exists
	 * @param by
	 * @return true if found
	 */
	public boolean elementExists(By by){
		return !findElements(by, -1).isEmpty();
	}

	/**
	 * Enters a char one at a time
	 * @param element
	 * @param text
	 * @return same element
	 */
	public Element slowSendKeys(Element element, String text){
		for( char c : text.toCharArray()){
			element.sendKeys(Character.toString(c));
		}
		return element;
	}

	/**
	 * switchFrame workaround. Normal switchFrame may sometimes fail, therefore
	 * this method serves as a retry method to retry switching to the frame.
	 *
	 * @param element:    By object of frame element
	 * @param maxRetries: Maximum number of retries
	 */
	protected void switchFrame(By element, int maxRetries) {
		boolean frameVisible = isFrameVisible(element);
		int currentRetries = 0;
		while (frameVisible && (currentRetries < maxRetries)) {
			switchFrame(element);
			currentRetries++;
			getWait();
			frameVisible = isFrameVisible(element);
		}
	}
	/**
	 * Checks if frame is visible
	 *
	 * @param frame (By): By element of iframe
	 * @return true if visible, false if not visible
	 */
	public boolean isFrameVisible(By frame) {
		try {
			getWait().until(ExpectedConditions.visibilityOfElementLocated(frame));
			return true;
		} catch (Exception e) {
			log.debug("iFrame is not visible");
		}
		return false;
	}

	/** Scrolls to element to avoid issues with element location being unclickable */
	public Element scrollTo(Element el){
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", el.element());
		}catch (Exception e){
			log.warn("scrolling to elemennt failed",e);
		}
		return el;
	}

	/**
	 * Checks if Spinner is present on the page
	 *
	 * @return true if spinner is present, false if it isn't present
	 */
	public boolean isSpinnerPresent() {
		By spinner = By.cssSelector("[class='spinner']");
		boolean spinnerPresent = false;
		try {
			getWait().until(ExpectedConditions.presenceOfElementLocated(spinner));
			spinnerPresent = true;
			log.debug("Spinner is present");
		} catch (Exception e) {
			log.debug("Spinner is not present");
		}
		return spinnerPresent;
	}
	/**
	 * Waits until Processing spinner is finished
	 */
	public void waitUntilProcessingDone() {
		int currentRetries = 0;
		int maxRetries = 10;
		boolean spinnerPresent;
		do {
			spinnerPresent = isSpinnerPresent();
			currentRetries++;
			if (spinnerPresent) getWait();
		}
		while ((spinnerPresent) && (currentRetries < maxRetries));
	}

	/**
	 * Enters a key and waits until the entered key is displayed in the textbox
	 *
	 * @param element
	 * @param text
	 * @return same element
	 */
	public Element sendKeysUntilTextIsDisplay(Element element, String text) {
		while (!element.getValue().equals(text)) {
			try {
				element = element.clear().sendKeys(text);
				wait.until(ExpectedConditions.textToBePresentInElementValue(element.element(), text));

			} catch (Exception e) {

			}
		}
		return element;
	}

	/**
	 * Checks if Spinner is present on the page
	 *
	 * @return true if spinner is present, false if it isn't present
	 */
	public boolean isSpinnerPresent(By by) {
		By spinner = by;
		boolean spinnerPresent = false;
		try {
			getWait().until(ExpectedConditions.presenceOfElementLocated(spinner));
			spinnerPresent = true;
			log.debug("Spinner is present");
		} catch (Exception e) {
			log.debug("Spinner is not present");
		}
		return spinnerPresent;
	}

	/**
	 * Waits until Processing spinner is finished
	 */
	public void waitUntilProcessingDone(By by) {
		int currentRetries = 0;
		int maxRetries = 10;
		boolean spinnerPresent;
		do {
			spinnerPresent = isSpinnerPresent(by);
			currentRetries++;
			if (spinnerPresent) getWait();
		}
		while ((spinnerPresent) && (currentRetries < maxRetries));
	}

	/**
	 * move to the top of the page
	 */
	public void moveToTop() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-2500)", "");
	}

	/**
	 * move to the top of the page
	 */
	public void scrollUp() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-500)", "");
	}


	public String getTextInPseudoElement(String cssLocator){
		String content="";
		try {
			((JavascriptExecutor) getDriver()).executeScript(cssLocator);
			content = (String) ((JavascriptExecutor) getDriver()).executeScript(cssLocator);
			return content;
		}catch (Exception e){
			log.warn("Unable to get text in Pseudo element",e);
		}
		return content;
	}

	/**
	 * move to the bottom of the page
	 */
	public void moveToBottom() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, 2500)", "");
	}

	/**
	 * refresh the page
	 */
	public void refreshPage() {
		getDriver().navigate().refresh();
	}

	/**
	 * Gets the shadowRoot Element
	 */
	public RemoteWebElement getShadowRootElement(WebElement shadow_host)
	{
		Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadow_host);
		String id = (String) ((Map<String, Object>) shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf");
		RemoteWebElement shadowRootElement = new RemoteWebElement();
		shadowRootElement.setParent((RemoteWebDriver) driver);
		shadowRootElement.setId(id);
		return shadowRootElement;
	}

	/**
	 * Open new tab
	 */
	public void openNewTab()
	{
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.open('about:blank','_blank');");
	}

}