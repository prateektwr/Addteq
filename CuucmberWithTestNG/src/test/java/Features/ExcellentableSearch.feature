Feature: This file contains search module scenarios for Excellentable

  Scenario: Verify the search module with positive input
    Given User is on Excellentable Page
    When User enters "Comments" in the search box
    Then User should see search suggestions containing "Comment"
    When User clicks on the first suggestion
    Then User should navigated to the "Comments" page

  Scenario Outline: Verify the search module with negative input
    Given User is on Excellentable Page
    When User enters "<SearchText>" in the search box
    Then User should see search suggestions containing "Your search returned no matches."

    Examples: 
      | SearchText       |
      | Firetruck        |
      | abc123@gmail.com |
      | @@@              |
