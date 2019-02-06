Feature: user can play the game against the AI

  Scenario: amount of rounds is calculated correctly
    Given user is at the main page
    And user plays "15" rounds against the AI
    Then Total amount of the scores and draws is "15"

  Scenario: ai wins if player uses a repeating pattern
    Given user is at the main page
    And user plays "50" rounds against using an "repeating" pattern
    Then player loses
