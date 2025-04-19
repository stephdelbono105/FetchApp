Author: Stephanie DelBono - stephdelbono@gmail.com

# FetchApp

An Android app built in Java that retrieves, filters, sorts, and displays data from a remote JSON endpoint. 
This project was developed as part of a coding exercise for the Fetch Rewards Android Engineer Apprentice role.

## Features

- Fetches data from: (https://hiring.fetch.com/hiring.json)
- Filters out any items where the `name` field is `null` or blank
- Sorts the results:
  - First by `listId`
  - Then by `name` (in ascending order)
- Groups items by `listId`
- Displays the grouped and sorted data in a clean, scrollable list (RecyclerView)

## Built With

- Java
- Kotlin
- Android SDK (latest stable release)
- AndroidX libraries
- RecyclerView for list display
- OkHttp for network requests
- Gson for JSON parsing

## Setup

1.) Clone the repository:
    https://github.com/stephdelbono105/FetchApp.git
   
2.) Open the project in Android Studio

3.) Let Gradle sync finish

4.) Run the app on a physical device or emulator (targeting latest stable Android OS)


## Make sure your build.gradle includes:

- implementation 'com.squareup.okhttp3:okhttp:4.12.0'
- implementation 'com.google.code.gson:gson:2.10.1'


## Requirements
- Android Studio Hedgehog or newer
- Android SDK 34+
- Internet permission (already included in AndroidManifest.xml)
