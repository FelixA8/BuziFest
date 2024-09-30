# Buzivest - MSME Investment App

**Buzivest** is a demo mobile application built to support Micro, Small, and Medium Enterprises (MSMEs) in securing funding from potential investors. The app serves as a platform where investors can explore various MSMEs, analyze their growth potential, and make investments to earn margins as these enterprises grow.

This app is developed using Kotlin and Firebase to provide real-time data, authentication, and database management.

## Features

- **MSME Catalog**: Browse and view a list of MSMEs, complete with detailed information about their business, financials, and potential growth.
- **Investor Search**: Investors can search for MSMEs based on criteria such as industry, potential growth, location, and more.
- **Firebase Integration**: Real-time database integration to store and retrieve MSME and investor information.
- **Google Sheets Integration**: Used for managing ongoing request from MSMEs.
- **Easy Authentication**: Integrated Firebase Authentication to secure investor and MSME logins.

## Technology Stack

- **Kotlin**: Main programming language used for Android app development.
- **Firebase**: Real-time database, authentication, and cloud storage.
- **REST API**: Integrated to fetch financial data and MSME statistics.
- **Google Sheets API**: For form submissions and data management.
- **Android SDK**: App is built using Android SDK for mobile development.

## Prerequisites

Before running the project, ensure you have the following:
- Android Studio installed.
- An Android device or emulator set up.
- Basic understanding of Kotlin and Android app development.
- API keys for Google Maps and the REST API (if required).

### Installation
1. Clone the repository:

   ```bash
   git clone https://github.com/FelixA8/BuziFest.git
   cd BuziFest

2. Open in Android Studio:
    - Click on File > Open and select the BuziFest project folder.
    - Let Android Studio sync the Gradle files and dependencies.

3. Set Up API Keys:
    - Get a Google Maps API key from the Google Cloud Console.
    - Set up your REST API URL in the Kotlin api/ files if the app requires it.
    - Add your API keys to the local.properties or in gradle.properties (or as instructed by the project setup).

4. Build and Run
    - Connect your Android device or set up an emulator.
    - Click on the Run button in Android Studio, and the app should start on your device.

## Project Structure

The project is organized into several key packages under `main/java/com/example/puffandpoof`:

    ```bash
    app/
    ├── Activity/         # Contains activities for different screens in the app
    ├── Adapter/          # Manages RecyclerView adapters for lists
    ├── Api/              # API-related classes for communicating with Firebase and Google Sheets
    ├── Data/             # Handles data management and models related to MSMEs and investors
    ├── Fragment/         # Contains fragment components for MSME and investor screens
    ├── Helper/           # Utility classes for common functions (e.g., form validation)
    ├── Model/            # Data models representing MSMEs, investors, and transactions
    └── MainActivity.kt   # Main activity

<p align="center">Empowering MSMEs with Kotlin and Firebase!</p>
