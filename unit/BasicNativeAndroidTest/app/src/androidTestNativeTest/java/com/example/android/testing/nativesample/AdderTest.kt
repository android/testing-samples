package com.example.android.testing.nativesample

import androidx.test.ext.junitgtest.GtestRunner
import androidx.test.ext.junitgtest.TargetLibrary
import org.junit.runner.RunWith


// Run our tests with the `GtestRunner`, which knows how to load and run GTest suites.
@RunWith(GtestRunner::class)
// Specify the name of the artifact which contains our tests, as configured in CMakeLists.txt
@TargetLibrary(libraryName = "adder-test")
class AdderTest