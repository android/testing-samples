# Basic sample for writing C/C++ GTest tests and running them on Android emulators / devices.

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
2. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
3. Check out the relevant code:
   * The C++ sources and tests are in `src/main/cpp`
   * The device tests (which wrap the native tests) are in `src/androidTest/java`
4. Create and run the Instrumented test configuration
   * Open the `AdderTest` file, and click the run icon in the gutter, or
   * Manually create a configuration.
      * Open *Run* menu | *Edit Configurations*
      * Add a new *Android Instrumented Tests* configuration
      * Choose the `app` module
      * Connect a device or start an emulator
      * Run the newly created configuration

If you are using Android Studio, the *Run* window will show the test results.
