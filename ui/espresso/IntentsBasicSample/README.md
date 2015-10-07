# Basic sample for Espresso Intents

Espresso Intents is a great way to do hermetic inter app testing. It works essentially like mockito and allows for Intent
verification and stubbing.

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
1. Open the Android SDK Manager (*Tools* Menu | *Android*) and make sure you have installed the *Android testing support library Repository* under *Extras*.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/androidTest/java`
1. Create the test configuration with a custom runner: `android.support.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Tests* configuration
    * Choose a module
    * Add a *Specific instrumentation runner*: `android.support.test.runner.AndroidJUnitRunner`
1. Connect a device or start an emulator
    * Turn animations off.
    (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.

If you are using Android Studio, the *Run* window will show the test results.
