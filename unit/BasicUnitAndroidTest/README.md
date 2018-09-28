# Basic sample for unit testing on device or emulator

An *Unit Android Test* is a test that needs an Android device or emulator but it's different
from a UI test because it doesn't start any activities.

In this sample the test can't run without the Android Framework because the Parcel class is used in
one of the methods of the Parcelable interface and the way data is written into a Parcel and read
from it is not trivial enough to be stubbed.

Note that the unit test is placed in `/androidTest/` instead of `/test/`.

This project uses the Gradle build system. You can either benefit from IDEs
integration such as Android studio or run the tests on the command line.

## Setup the project in Android studio and run the tests.

1. Download the project code, preferably using `git clone`.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
    * `LogHistory.java` is the class under test. It implements Parcelable.
    * `LogHistoryAndroidUnitTest` is the Android unit test
    * `MainActivity.java` shows the Parcelable in action but note that the test is not showing any
activities on the device or emulator.
1. Create the test configuration with a custom runner: `androidx.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Tests* configuration
    * Choose a module
    * Add a *Specific instrumentation runner*: `androidx.test.runner.AndroidJUnitRunner`
1. Connect a device or start an emulator
1. Run the newly created configuration

The unit test will be ran automatically.

## Use Gradle on the command line.

After downloading the projects code using `git clone` you'll be able to run the
unit tests using the command line:

    ./gradlew connectedCheck

If all the unit tests have been successful you will get a `BUILD SUCCESSFUL`
message.

## See the report.

A report in HTML format is generated in `app/build/outputs/reports/androidTests/connected`
