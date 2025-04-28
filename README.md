# REST Assured Test Automation Framework

## Overview
A TestNG-based REST API testing framework built using REST Assured for testing the Restful-Booker API. The framework implements a modular design pattern with robust logging and retry mechanisms.

## Table of Contents
1. [Framework Structure](#framework-structure)
2. [Prerequisites](#prerequisites)
3. [Dependencies](#dependencies)
4. [Setup Instructions](#setup-instructions)
5. [Framework Components](#framework-components)
6. [Test Implementation](#test-implementation)
7. [API Operations](#api-operations)
8. [Logging System](#logging-system)
9. [Best Practices](#best-practices)
10. [Troubleshooting Guide](#troubleshooting-guide)

## Framework Structure 

RestAssured
├── src
│   ├── main/java/utilities
│   │   ├── EndPoints.java
│   │   ├── RestClientWrapper.java
│   │   └── TestListeners.java
│   │
│   └── test/java
│       ├── json_pojo_data
│       │   └── Json_data_pojo_class_login.java
│       │
│       ├── payloads
│       │   ├── dynamic_auth_data.java
│       │   ├── pojo_booking_create_data.java
│       │   ├── pojo_booking_dates_data.java
│       │   ├── pojo_class_auth_data.java
│       │   └── pojo_login_data.java
│       │
│       ├── repeatables
│       │   ├── BasePage.java
│       │   ├── data_pojo_class_create.java
│       │   ├── data_pojo_class_login.java
│       │   ├── data_pojo_class_partial_update.java
│       │   └── data_pojo_class_update.java
│       │
│       └── testCase
│           ├── testCase1.java
│           └── testCase2.java
│
└── testData
    ├── auth_data.json
    ├── create_booking.json
    ├── partial_update_data.json
    └── update_booking_data.json
```

## Prerequisites
- Java JDK 8 or higher
- Maven 3.6.3 or higher
- Git
- IDE (IntelliJ IDEA or Eclipse with TestNG plugin)

## Dependencies
The framework uses the following main dependencies:
- TestNG for test execution
- REST Assured for API testing
- Jackson for JSON processing
- Log4j2 for logging

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd RestAssured
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests
```bash
mvn test
```

## Framework Components

### 1. Utilities
- **EndPoints.java**: Contains all API endpoint definitions
- **RestClientWrapper.java**: Wrapper methods for REST Assured
- **TestListeners.java**: TestNG listener implementation

### 2. Test Data
- JSON files for request data
- POJO classes for data mapping
- Test data in testData directory

### 3. Test Cases
Two main test classes:
- **testCase1.java**: Basic booking operations
- **testCase2.java**: Advanced booking operations

## Test Implementation
Example test method:
```java
@Test(priority=1)
public void create_token_from_Auth() throws Exception {
    String body_data = Json_data_pojo_class_login.login_data();
    Response response = restClient.post_Authorization(EndPoints.auth, body_data)
        .then()
        .assertThat()
        .statusCode(200)
        .extract()
        .response();
    // Assertions
}
```

## API Operations
The framework covers the following API operations:

1. **Authentication**
   - Token generation
   - Token validation

2. **Booking Operations**
   - Create new booking
   - Update existing booking
   - Partial update booking
   - Get booking details
   - Delete booking

## Logging System
The framework implements comprehensive logging using Log4j2:
- Test execution status
- API request/response details
- Error logging
- Test results

## Best Practices

### 1. Test Design
- Write independent tests
- Use meaningful test names
- Implement proper assertions
- Follow TestNG annotations properly

### 2. Code Organization
- Maintain proper package structure
- Use POJO classes for data handling
- Keep test data separate from code

### 3. Error Handling
- Implement proper exception handling
- Use appropriate assertions
- Log relevant error messages

## Troubleshooting Guide

### Common Issues and Solutions

1. **Test Execution Failures**
   - Check API endpoint availability
   - Verify JSON request data
   - Validate authentication token
   - Review test logs

2. **Authentication Issues**
   - Verify auth_data.json content
   - Check token generation process
   - Validate token usage in requests

3. **Data-Related Issues**
   - Check JSON file formatting
   - Verify POJO class mapping
   - Validate request/response data

### Resolution Steps
1. Review test logs
2. Check API endpoint status
3. Verify request payload
4. Validate JSON data format
5. Check authentication token

