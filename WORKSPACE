load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:jvm.bzl", "jvm_maven_import_external")

# Set the path to your local SDK installation, or use the ANDROID_HOME environment variable.
android_sdk_repository(
    name = "androidsdk",
    api_level = 28,
    build_tools_version = "28.0.2",
    # path = "/path/to/sdk",
)

# Android Test Support
#
# This repository contains the supporting tools to run Android instrumentation tests,
# like the emulator definitions (android_device) and the device broker/test runner.
ATS_TAG = "androidx-test-1.1.1-alpha01"

ATS_SHA256 = "f7e967cb471bc279fda564084e965868d96e6c608fa27e26cf4330ae29cd603e"


http_archive(
    name = "android_test_support",
    sha256 = ATS_SHA256,
    strip_prefix = "android-test-%s" % ATS_TAG,
    urls = ["https://github.com/android/android-test/archive/%s.tar.gz" % ATS_TAG],
)

load("@android_test_support//:repo.bzl", "android_test_repositories")

android_test_repositories()

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "1.0"
RULES_JVM_EXTERNAL_SHA = "48e0f1aab74fabba98feb8825459ef08dcc75618d381dff63ec9d4dd9860deaa"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

# Keeping a copy of gmaven_rules around so dependencies can use it
# TODO(jin): remove this when android/android-test no longer depends on gmaven_rules.
http_archive(
    name = "gmaven_rules",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("//:common_defs.bzl",
     "androidxLibVersion",
     "coreVersion",
     "espressoVersion",
     "extJUnitVersion",
     "extTruthVersion",
     "rulesVersion",
     "runnerVersion",
     "uiAutomatorVersion",
)

maven_install(
    name = "maven",
    artifacts = [
        "androidx.annotation:annotation:" + androidxLibVersion,
        "androidx.core:core:" + androidxLibVersion,
        "androidx.recyclerview:recyclerview:" + androidxLibVersion,
        "androidx.test:core:" + coreVersion,
        "androidx.test.espresso:espresso-contrib:" + espressoVersion,
        "androidx.test.espresso:espresso-core:" + espressoVersion,
        "androidx.test.espresso:espresso-idling-resource:" + espressoVersion,
        "androidx.test.espresso:espresso-intents:" + espressoVersion,
        "androidx.test.ext:junit:" + extJUnitVersion,
        "androidx.test.ext:truth:" + extTruthVersion,
        "androidx.test:monitor:" + runnerVersion,
        "androidx.test:rules:" + rulesVersion,
        "androidx.test:runner:" + runnerVersion,
        "androidx.test.uiautomator:uiautomator:" + uiAutomatorVersion,
        "com.google.inject:guice:4.0",
        "junit:junit:4.12",
        "javax.inject:javax.inject:1",
        "org.hamcrest:java-hamcrest:2.0.0.0",
        "com.google.guava:guava:26.0-android",
        "com.google.truth:truth:0.42",
    ],
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
)
