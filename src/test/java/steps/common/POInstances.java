package steps.common;

import pageobjects.*;
import pageobjects.common.BasePage;
import pageobjects.demowebshop.*;
import pageobjects.medicalRecord.HomePage;
import pageobjects.medicalRecord.LoginPage;
import pageobjects.medicalRecord.RegistrationPage;
import utils.*;

public class POInstances {
    protected UserProfileHelper userProfileHelper = new UserProfileHelper();
    protected UserLoginHelper userLoginHelper = new UserLoginHelper();
    protected ProductsHelper productsHelper = new ProductsHelper();
    protected TranslationsHelper translationsHelper = new TranslationsHelper();
    protected RegistrationPage registrationPage = new RegistrationPage();
    protected NavigationPage navigationPage = new NavigationPage();
    protected GooglePage googlePage = new GooglePage();
    protected UrlHelper urlHelper = new UrlHelper();
    protected BasePage basePage = new BasePage();
    protected LoginPage loginPage = new LoginPage();
    protected HomePage homePage = new HomePage();
    protected ShopHomePage shopHomePage = new ShopHomePage();
    protected ShopCartPage shopCartPage = new ShopCartPage();
    protected ShopCheckoutPage shopCheckoutPage = new ShopCheckoutPage();
    protected ShopLoginPage shopLoginPage = new ShopLoginPage();

}