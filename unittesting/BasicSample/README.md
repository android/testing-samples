# Basic sample for writing unit tests that mocks the Android framework

*If you are new to unit testing on Android, try this sample first.*

This project uses the Gradle build system and the Android gradle plugin support for unit testing.
You can either benefit from IDEs integration such as Android studio or run the tests on the command
line.

Unit tests run on a local JVM on your development machine. The Android Gradle plugin will compile
your app's source code and execute it using gradle test task. Tests are executed against a modified
version of android.jar where all final modifiers have been stripped off. This lets you use popular
mocking libraries, like Mockito.

For more information see http://tools.android.com/tech-docs/unit-testing-support

## Setup the project in Android studio and run tests.

1. Download the project code, preferably using `git clone`.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Make sure you select "Unit Tests" as the test artifact in the "Build Variants" panel in Android Studio. 
1. Check out the relevant code:
    * The application code is located in `src/main/java`
    * Unit Tests are in `src/test/java`
1. Create a test configuration with the JUnit4 runner: `org.junit.runners.JUnit4`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *JUnit* configuration
    * Choose module *app*
    * Select the class to run by using the *...* button
1. Run the newly created configuration

The unit test will be ran automatically.

## Use Gradle on the command line.

After downloading the projects code using `git clone` you'll be able to run the
unit tests using the command line:

    ./gradlew test

If all the unit tests have been successful you will get a `BUILD SUCCESSFUL`
message.

## See the report.

A report in HTML format is generated in `app/build/reports/tests`
