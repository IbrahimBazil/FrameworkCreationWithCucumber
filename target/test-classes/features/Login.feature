Feature: Signup on the Registration Page
Scenario Outline: Opening Registration Page
Given Initialize the Browser
And lauch the url "QAClickAcademy"
And Click on the Signin button
When user Enters the <username> with password <password>
Then validate the error message

Examples:
|username	|password	|
|Ibrahim	|pwd			|
|ibragimbazil@gmail.com|password|

Scenario: Successfull Signin
Given Initialize the Browser
And lauch the url "QAClickAcademy"
And Click on the Signin button
When user Enters the "ibrahimbazil@gmail.com" with password "Hashim@3021" for happy
Then userloggedin successfully
And close the driver
