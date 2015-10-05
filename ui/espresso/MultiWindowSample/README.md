# Multi-window sample for Espresso

Android's Window system allows multiple view hierarchies to layer on top of each other.

A real world analogy would be an overhead projector with multiple transparencies placed
on top of each other. Each Window is a transparency, and what is drawn on top of this
transparency is the view hierarchy.

By default Espresso uses a heuristic to guess which Window you intend to interact with.
This heuristic is normally 'good enough' however if you want to interact with a Window
that it does not select then you'll have to swap in your own root window matcher.
Initially there's only one window, but typing into the auto-complete text view creates another
window that will be layered on top of the screen. Espresso ignores this layer because it is
not connected to the keyboard/ime.

Espresso provides the ability to switch the default window matcher used in both onView and onData
interactions.

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
