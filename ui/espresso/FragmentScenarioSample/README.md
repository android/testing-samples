# Sample for FragmentScenario with Espresso

*A simple example that shows use of FragmentScenario with Espresso.*

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio 3.4 is recommended.

1. Download the project code, preferably using `git clone`.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * The test source is located in `src/sharedTest/java`. There is a single set of test source that can be executed
    either on local host using Robolectric or on emulator/device.
1. Create and runthe Android Instrumented Test configuration
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Instrumented Tests* configuration
    * Choose the `app` module
    *. Connect a device or start an emulator
    * Turn animations off.
    (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
    * Run the newly created configuration
1. Create and run the local Test configuration
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android JUnit * configuration
    * Set 'Use classpath of modul` to `app`
    * Set `Class` to FragmentScenarioTest
    * Run the configuration

If you are using Android Studio, the *Run* window will show the test results.
