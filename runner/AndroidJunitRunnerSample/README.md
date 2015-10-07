# AndroidJUnitRunner sample

The new android test runner brings Junit4 support to android testing. This samples gives a quick
overview of some of the new features like test annotations, parameterized tests and test suite
creation.

1. CalculatorTest.java contains JUnit4 style unit tests for the calculator logic.
1. CalculatorAddParameterizedTest.java contains JUnit4 style tests and uses the @Parameters annotation
   to parameterize a single test with different values.
1. CalculatorInstrumentationTest.java uses JUnit4 style tests to test the Ui of the CalculatorActivity
1. OperationHintInstrumentationTest.java uses JUnit3 style tests to test the Ui of the CalculatorActivity

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
1. Open the Android SDK Manager (*Tools* Menu | *Android*) and make sure you have installed the *Android Support Repository* under *Extras*.
1. In Android Studio, select *File* | *Open...* and point to the top-level `./build.gradle` file.
1. Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/androidTest/java`
1. Create the test configuration with a custom runner: `android.support.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Tests* configuration
    * Choose a module
    * Choose which tests to run. Click on Test: class and select one of the TestSuites
    (AndroidTestSuite, UnitTestSuite, InstrumentationTestSuite)
    * Add a *Specific instrumentation runner*: `android.support.test.runner.AndroidJUnitRunner`
1. Connect a device or start an emulator
    * Turn animations off.
    (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.

If you are using Android Studio, the *Run* window will show the test results.
