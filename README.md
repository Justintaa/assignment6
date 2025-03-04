# BarnesAndNoble Testing Automation

## Project Overview
This project is designed to test the `BarnesAndNoble` bookstore system, ensuring its functionality through **specification-based** and **structural-based** testing. The main goal of this project is to verify the correctness of the system's behavior and interactions with various components such as the book database and the book purchasing process.

Additionally, the project automates the testing and static analysis of the code using **GitHub Actions**, maintaining code quality and integrity throughout the development process.

## Technologies Used
- **Java** – The programming language used for implementing the system and tests.
- **JUnit 5** – A testing framework for writing and running tests.
- **Mockito** – A mocking framework used to simulate dependencies in the testing environment.
- **GitHub Actions** – A CI/CD platform used to automate the build, test, and deployment pipeline.
- **Checkstyle** – A static analysis tool used for code quality checks.
- **JaCoCo** – A code coverage tool used to measure test coverage.

## Features
- **Automated Testing**: The project includes both unit tests and integration tests.
- **Static Analysis**: Checkstyle is integrated to analyze code for style violations.
- **Code Coverage**: JaCoCo is used to measure test coverage and ensure quality.
- **CI/CD Pipeline**: The project utilizes GitHub Actions for continuous integration and delivery.

## How to Run Tests Locally
To run the tests on your local machine:

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/your-repo-name.git

2. Navigate to the project directory:
    cd BarnesAndNoble

3. Run the tests:
    mvn test


GitHub Actions - CI/CD Pipeline

This project uses GitHub Actions to automate the following processes:
* Static Analysis
* Unit Tests
* Code Coverage


Repository Structure

* src/: Contains the source code files.
* tests/: Contains the test classes for specification-based and structural-based tests.
* .github/workflows/: Contains the GitHub Actions workflow configuration.
* pom.xml: The Maven project configuration file.
* README.md: This file.
* GitHub Repository
  https://github.com/Justintaa/assignment6