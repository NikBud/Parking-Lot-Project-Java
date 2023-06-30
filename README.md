
# Parking Lot
Parking Lot is an iOS app developed as an internal project for the Endava 2023 Spring Internship Programme.

## Table of Contents
* [Description](#description)
* [Installation](#installation)
* [Report Bugs](#report-bugs)
* [Tests](#tests)
* [Contact](#contact)

## Description
The Parking Lot App is a user-friendly mobile application designed to simplify and enhance the parking experience for both drivers and parking lot operators. This app serves as a convenient digital platform that allows users to easily locate and reserve parking spaces, reducing the stress and time spent searching for parking.

## Installation
To run the Parking Lot App, follow these steps:

1. Install [Rancher Desktop](https://github.com/rancher-sandbox/rancher-desktop/releases/) to set up the necessary environment for the application.

2. Configure the container image to use the default "docker(moby)" setting.

3. Set the active profile for the application to connect to the development database by running the application in your IDE with the Environmental Variable:

`dev`

This command ensures that the app connects to the appropriate database configuration specified in the `dev-application.properties` file.

## Report Bugs
If you would like to report a bug please open an issue [here](https://github.com/eldlit/parking-lot/issues).

## Tests
To run Unit Tests use `mvn surefire:test`

To run Integration Tests use `mvn failsafe:integration-test`

## Contact

If you have any questions, feedback, or issues related to the Parking Lot App, please reach out to us on Microsoft Teams.

Thank you for using the Parking Lot App!