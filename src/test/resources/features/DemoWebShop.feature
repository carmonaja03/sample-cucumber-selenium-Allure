Feature: Demo Web Shop
  @RegressionSuite @done
  Scenario: Add product to cart
    Given "DemoWebShop" is open
    When "ShopUser" user inputs "userEmail" and "password" in the webshop login page
    And clicks the log in button
    Then "ShopUser" user "userEmail" is now logged in
    And "ShopUser" clicks "productCategory1" in the top header menu
    And "ShopUser" user selects "product1"
    And user clicks add to cart button
    Then user confirms that the product has been added to cart
    And user clicks shopping cart
    And "ShopUser" user updates product quantity to "product_qty_mininum"
    And user checks agree with the terms and conditions
    #When user clicks checkout button
    #Then user can see the Checkout label
    #When "ShopUser" user inputs "first_name" billing address first name
    #And "ShopUser" user inputs "last_name" billing address last name
    #And "ShopUser" user inputs "email" billing address email
    #And "ShopUser" user selects "country" billing address country
    #And "ShopUser" user inputs "city" billing address city
    #And "ShopUser" user inputs "postal_code" billing address postal code
    #And "ShopUser" user inputs "phone_number" billing address phone number