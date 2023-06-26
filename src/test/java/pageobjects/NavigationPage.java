package pageobjects;

import automation.library.selenium.core.Element;
import automation.library.selenium.exec.BasePO;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import pageobjects.common.BasePage;
import utils.UrlHelper;

import java.io.IOException;
import java.util.List;

public class NavigationPage extends BasePage {

	public void navigateToURL(String urlKey) throws Exception {
		String url = String.valueOf(UrlHelper.getURLEnv(urlKey));
		maximizeViewPort();
		navigateToPage(url);
	}
}
