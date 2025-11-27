🧪 SwagLabs Automation Testing Framework

Selenium + TestNG + Java + Maven + Allure Reports

This repository contains a complete automation testing framework for the SwagLabs web application (https://www.saucedemo.com/).
The framework covers login, product interactions, cart flow, and complete checkout process using Page Object Model (POM), TestNG, and Allure reporting.


---

📂 Project Structure (as shown in your IDE)

src
├── main
│   └── java
│       ├── DriverFactory
│       ├── Pages
│       │     ├── P01_LoginPage
│       │     ├── P02_HomePage
│       │     ├── P03_CartPage
│       │     ├── P04_CheckoutPage
│       │     ├── P05_SecondCheckoutPage
│       │     └── P06_CheckoutCompletePage
│       └── Utilities
│
├── test
│   ├── java
│   │     ├── Listeners
│   │     └── Tests
│   │           ├── TC01_LoginTest
│   │           ├── TC02_HomeTest
│   │           ├── TC03_CartTest
│   │           ├── TC04_CheckoutTest
│   │           ├── TC05_SecondCheckoutPageTest
│   │           └── TC_06CheckoutCompleteTest
│   │
│   └── resources
│         └── TestData
│               ├── CheckoutForm.json
│               ├── validLogin.json
│               └── environment.properties
│
├── Test Runner (TestNG XML files)
└── test-outputs


---

🚀 Tech Stack

Java 21 / 25

Selenium WebDriver

TestNG

Maven

Allure Reporting

Page Object Model (POM)

Listeners (ITestListener)

JSON Test Data

Properties File for Environments

Driver Factory Pattern



---

✔ Test Coverage

🔐 Login Module

Valid login using JSON test data

Invalid login scenarios

Locked-out user behavior


🏠 Home Page

Product listing validation

Sorting items

Navigation between pages


🛒 Cart Module

Add/remove products

Verify items count

Check price totals


💳 Checkout Module (Multi-step)

1. Checkout Page 1 → User Information


2. Checkout Page 2 → Price overview + tax


3. Order Completion Page → Final success validation




---

🧱 Framework Architecture

1. Page Object Model (POM)

Every page has its own class:

Login → P01_LoginPage

Home → P02_HomePage

Cart → P03_CartPage

Checkout Steps → P04, P05, P06


2. DriverFactory

Centralized WebDriver creation and setup (Chrome, Edge, etc.)

3. Utilities

Shared helper methods:

Waits

Config reader

JSON parser

Screenshot utility


4. Listeners

Automatic screenshots on failure

Test step logging

Allure attachment support


5. Test Data

Stored in /TestData/:

validLogin.json

CheckoutForm.json

environment.properties



---

▶ How to Run the Tests

⿡ Clone the Repository

git clone https://github.com/khaled-mo11/SwagLabs-AutomationTesting.git

⿢ Install Maven Dependencies

mvn clean install

⿣ Execute All Tests

mvn test

⿤ Generate Allure Report

allure serve allure-results


---

📸 Screenshots & Reporting

Allure attaches screenshots automatically on test failure

Test steps and assertions appear in the HTML report

You can generate history trends, categories, and timelines



