# AndroidTestOrchestrator with test coverage sample

The Android Test Orchestrator allows you to run each of your app's tests in isolation, enabling greater reliability.
See https://developer.android.com/training/testing/junit-runner#using-android-test-orchestrator for more background.

This sample is a subset of the AndroidJUnitRunner sample, but it
illustrates how to enable Jacoco test coverage report with the Android Test Orchestrator in the app/build.gradle file.

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
1. Open the Android SDK Manager (*Tools* Menu | *Android*).
1. In Android Studio, select *File* | *Open...* and point to the top-level `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/androidTest/java`
1. Create the test configuration with a custom runner: `androidx.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Tests* configuration
    * Choose a module
    * Add a *Specific instrumentation runner*: `androidx.test.runner.AndroidJUnitRunner`
1. Connect a device or start an emulator
    * Turn animations off.
    (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.

If you are using Android Studio, the *Run* window will show the test results.
The test coverage report will be generated in `app/build/reports/coverage/androidTest/debug/index.html`.
