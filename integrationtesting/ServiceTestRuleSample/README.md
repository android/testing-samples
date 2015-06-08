# Basic sample for ServiceTestRule

This rule provides a simplified mechanism to start and shutdown your service before and after
the duration of your test. It also guarantees that the service is successfully connected when starting
(or binding to) a service. The service can be started (or bound) using one of the helper methods.
It will automatically be stopped (or unbound) after the test completes and any methods annotated with @After are finished.

Note: This rule doesn't support `IntentService` because it's automatically destroyed when
`IntentService#onHandleIntent(android.content.Intent)`
 finishes all outstanding commands. So there is no guarantee to establish a successful connection in a timely manner.

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
1. Open the Android SDK Manager (*Tools* Menu | *Android*) and make sure you have installed the *Support Repository* under *Extras*.
1. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/androidTest/java`
1. Connect a device or start an emulator
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.

If you are using Android Studio, the *Run* window will show the test results.
