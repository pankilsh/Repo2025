Feature: Purchase the order from Ecom website

Background:
Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive test to submit the order
Given Logged in with username <user_name> and password <password>
When I Add the product <product_name>
And Checkout <product_name> and submit order
Then "THANKYOU FOR THE ORDER." is displayed
Examples:
	|	user_name 			|	password			|	product_name		|
	|	pankil@gmail.com		|	Pass@12345		|	ZARA COAT 3		|
