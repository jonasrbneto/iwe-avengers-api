Feature: Perform integrated tests on the Avengers registration API

  Background: 
    * url 'https://udsnzlijn8.execute-api.us-east-1.amazonaws.com/dev/'

  Scenario: Creates a new Avenger
    Given path 'avengers'
    And request {name:'Captain America', secretIdentity:'Steve Rogers'}
    When method post
    Then status 201
    And match response == {id: '#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}
    
    * def savedAvenger = response
    
    Given path 'avengers', savedAvenger.id
    When method get
    Then status 200
    And match $ == savedAvenger

  Scenario: Creates a new Avenger without the requied data
    Given path 'avengers'
    And request {name: 'Captain America'}
    When method post
    Then status 400

  Scenario: Delete a Avenger
    Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
    When method delete
    Then status 204

  Scenario: Update a Avenger
    Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
    And request {name:'Hulk', secretIdentity:'Doctor Banner'}
    When method put
    Then status 200
    And match response == {id: '#string', name: '#string', secretIdentity: '#string'}

  Scenario: Update a Avenger without name
    Given path 'avengers', 'aaaa-bbbb-cccc-dddd'
    And request {name:'Hulk'}
    When method put
    Then status 400
