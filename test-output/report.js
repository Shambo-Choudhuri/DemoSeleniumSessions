$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/Users/user/Documents/EclipseProjects/DemoSeleniumSessions/src/main/java/com/qa/hubspot/FeatureFiles_BDD/login.feature");
formatter.feature({
  "line": 1,
  "name": "HubSpot Login Feature",
  "description": "",
  "id": "hubspot-login-feature",
  "keyword": "Feature"
});
formatter.scenario({
  "comments": [
    {
      "line": 4,
      "value": "#Without Examples Keyword Approach"
    }
  ],
  "line": 6,
  "name": "HubSpot Login Test Scenario",
  "description": "",
  "id": "hubspot-login-feature;hubspot-login-test-scenario",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "User is currently present on the Login Page",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "Title of Login Page is HubSpot Login",
  "keyword": "When "
});
formatter.step({
  "comments": [
    {
      "line": 12,
      "value": "#Then User enters \"shambo.choudhuri9@gmail.com\" and \"pHc#m8m6\""
    }
  ],
  "line": 14,
  "name": "User enters username and password",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "User lands on Home Page",
  "keyword": "Then "
});
formatter.match({
  "location": "LoginStepDefinition.user_is_currently_present_on_the_Login_page()"
});
formatter.result({
  "duration": 13127190700,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.title_of_Login_page_is_HubSpot_Login()"
});
formatter.result({
  "duration": 108909100,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.user_enters_username_and_password()"
});
formatter.result({
  "duration": 43400,
  "status": "passed"
});
formatter.match({
  "location": "LoginStepDefinition.user_lands_on_Home_Page()"
});
formatter.result({
  "duration": 39200,
  "status": "passed"
});
});