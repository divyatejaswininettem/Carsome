#Author: divya@gmail.com
@carsome
Feature: Verification of Perodua Axia car in Carsome website

  
  Scenario: To verify Perodua Axia is available in Carsome
    Given user is on carsome homepage
    Then click on Buy Car and select Perodua from the dropdown menu
    And Verify Perodua Axia cars available to buy

 
  Scenario: To verify the price of available Perodua Axia can be sorted from lowest to highest.
    Given Recommended dropdown and change it to Lowest Price
    And Verify that the price of the first car is lower than the second car
