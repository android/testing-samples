load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:jvm.bzl", "jvm_maven_import_external")

# Set the path to your local SDK installation, or use the ANDROID_HOME environment variable.
android_sdk_repository(
    name = "androidsdk",
    api_level = 28,
    build_tools_version = "28.0.2",
    # path = "/path/to/sdk",
)

# Required for running emulator tests on RBE on Bazel CI
android_ndk_repository(name = "androidndk")

# Android Test Support
#
# This repository contains the supporting tools to run Android instrumentation tests,
# like the emulator definitions (android_device) and the device broker/test runner.
ATS_TAG = "1edfdab3134a7f01b37afabd3eebfd2c5bb05151"

ATS_SHA256 = "dcd1ff76aef1a26329d77863972780c8fe1fc8ff625747342239f0489c2837ec"

http_archive(
    name = "android_test_support",
    sha256 = ATS_SHA256,
    strip_prefix = "android-test-%s" % ATS_TAG,
    urls = ["https://github.com/android/android-test/archive/%s.tar.gz" % ATS_TAG],
)

load("@android_test_support//:repo.bzl", "android_test_repositories")

android_test_repositories()

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "3.1"

RULES_JVM_EXTERNAL_SHA = "e246373de2353f3d34d35814947aa8b7d0dd1a58c2f7a6c41cfeaff3007c2d14"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")
load(
    "//:common_defs.bzl",
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
        "androidx.test.espresso:espresso-accessibility:" + espressoVersion,
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
        "androidx.viewpager:viewpager:1.0.0",
        maven.artifact(
            "com.google.inject",
            "guice",
            "4.0",
            neverlink = True,
        ),
        "junit:junit:4.12",
        "javax.inject:javax.inject:1",
        "org.hamcrest:java-hamcrest:2.0.0.0",
        maven.artifact(
            "org.robolectric",
            "robolectric",
            "4.3-beta-1",
            neverlink = True,
            exclusions = ["com.google.guava:guava"],
        ),
        "com.google.guava:guava:26.0-android",
        "com.google.truth:truth:0.42",
        "com.google.android.apps.common.testing.accessibility.framework:accessibility-test-framework:2.0",
    ],
    jetify = True,
    repositories = [
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
    version_conflict_policy = "pinned",
)

http_archive(
    name = "bazel_toolchains",
    sha256 = "4d348abfaddbcee0c077fc51bb1177065c3663191588ab3d958f027cbfe1818b",
    strip_prefix = "bazel-toolchains-2.1.0",
    urls = [
        "https://github.com/bazelbuild/bazel-toolchains/releases/download/2.1.0/bazel-toolchains-2.1.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-toolchains/archive/2.1.0.tar.gz",
    ],
)

load("@bazel_toolchains//rules:rbe_repo.bzl", "rbe_autoconfig")

rbe_autoconfig(name = "rbe_default")
