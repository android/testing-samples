# Basic sample for Espresso

*If you are new to Espresso, try this sample first.*

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
1. Create and run the local Test configuration
    * Open Run menu | Edit Configurations
    * Add a new *Android JUnit * configuration
    * Set `Use classpath of module` to `app`
    * Set `Class` to `ChangeTextBehaviorLocalTest`
    * Run the configuration    
    * The test will run on local host

If you are using Android Studio, the *Run* window will show the test results.
