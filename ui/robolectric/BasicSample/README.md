# Basic sample for Robolectric

*If you are new to Robolectric, try this sample first.*

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/test/java`
1. Create the test configuration:
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android JUnit Tests* configuration
    * Choose a module
    * Set the working directory to the module directory.
1. Run the newly created configuration

If you are using Android Studio, the *Run* window will show the test results.
