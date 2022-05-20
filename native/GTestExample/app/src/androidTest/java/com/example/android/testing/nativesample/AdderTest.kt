package com.example.android.testing.nativesample

import androidx.test.ext.junitgtest.GtestRunner
import androidx.test.ext.junitgtest.TargetLibrary
import org.junit.runner.RunWith

@RunWith(GtestRunner::class)
@TargetLibrary(libraryName = "adder-test")
class AdderTest