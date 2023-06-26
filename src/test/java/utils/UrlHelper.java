package utils;

import automation.library.common.Property;
import automation.library.common.TestContext;

import java.net.URL;

public class UrlHelper {

    public static URL getURLEnv(String key) throws Exception {
        return new URL (Property.getProperties(TestContext.getInstance().testdataGet("env").toString()).getString(key));
    }
}
