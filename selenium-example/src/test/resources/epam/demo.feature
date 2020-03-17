@requires_browser
Feature: demo
    Scenario: simple demo 
     Given a google page
      When I enter the search term "cats"
      And I submit the search by pressing "search"
      Then the search result page title should contain the search term
     
 