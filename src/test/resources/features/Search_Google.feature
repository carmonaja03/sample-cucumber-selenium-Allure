Feature: Google Search


  Scenario: Search on Google
    Given "GooglePage" is open
    And user "google_user" inputs "search_text_1" text on search textbox
    Then user "google_user" validates that "hello_world_url" is displayed

  Scenario Outline: Google Search using Scenario Outline
    Given "GooglePage" is open
    And user "<user>" inputs "<searchtext>" text on search textbox
    Then user "<user>" validates that "<url>" is displayed

    Examples:
      | user        | searchtext    | url             |
      | google_user | search_text_2 | selenium_url    |
      | google_user | search_text_3 | java_url        |