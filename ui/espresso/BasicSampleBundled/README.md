# Basic sample for Espresso using static JAR files

*If you are using Espresso with Eclipse, try this sample first.*

This project uses Eclipse and ADT to build and run the tests.

1. Download the project code, preferably using `git clone`.
1. Check out the static JAR files required to run the Espresso tests in the libs/ folder of the project
1. Run the ./remove_license.sh script to remove duplicated LICENSE.txt files. This step is required because some of the test dependencies contain LICENSE.txt files which result in this build error:
"Error generating final archive: Found duplicate file for APK: LICENSE.txt".
The problem is that the same LICENSE.TXT file is found multiple times and AAPT does not know how to resolve this conflict.
1. Check out the relevant code:
    * The application under test is located in `src/`
    * Tests are in `tests/`
1. Create the test configuration with a custom runner: `android.support.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Run Configurations*
    * Click on Android JUnit Test
    * Add a new configuration by pressing the "new launch configuration" button
    * Select your project by clicking the "Browse" button
    * Add a *Specific instrumentation runner*: `android.support.test.runner.AndroidJUnitRunner`
1. Connect a device or start an emulator
    * Turn animations off.
    (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
1. Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.
