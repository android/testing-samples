# Basic sample for Espresso Device

The Espresso Device API enables synchronized interactions with the test device. This API is experimental and subject to change. Currently, it is only supported on instrumentation tests run on emulators.

To skip tests on devices that do not have certain display attributes, such as display width and height, annotate your test with @RequiresDisplay. This annotation takes in a WidthSizeClassEnum and a HeightSizeClassEnum. It can be applied to test classes or test methods. For details on these size classes, see https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes.

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio 3.4 is recommended.

1. Download the project code, preferably using `git clone`.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
   * The application under test is located in `src/main/java`
   * Instrumentation Tests are in `src/androidTest/java`
   * Local Tests are in `src/test/java`
1. Create and run the Instrumented test configuration
   * Open *Run* menu | *Edit Configurations*
   * Add a new *Android Instrumented Tests* configuration
   * Choose the `app` module
   * Connect a device or start an emulator
   * Turn animations off.
     (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
   * Run the newly created configuration
   * The application will be started on the device/emulator and a series of actions will be performed automatically.

If you are using Android Studio, the *Run* window will show the test results.