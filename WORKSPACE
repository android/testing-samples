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

# Google Maven Repository
# This repository contains the external dependency definitions for Google Maven artifacts.
GMAVEN_TAG = "20181206-1"

http_archive(
    name = "gmaven_rules",
    strip_prefix = "gmaven_rules-%s" % GMAVEN_TAG,
    url = "https://github.com/bazelbuild/gmaven_rules/archive/%s.tar.gz" % GMAVEN_TAG,
)

load("@gmaven_rules//:gmaven.bzl", "gmaven_rules")

gmaven_rules()

jvm_maven_import_external(
    name = "com_google_inject_guice",
    server_urls = ["http://central.maven.org/maven2"],
    licenses = ["notice"], # Apache 2.0
    artifact = "com.google.inject:guice:4.0",
    artifact_sha256 = "b378ffc35e7f7125b3c5f3a461d4591ae1685e3c781392f0c854ed7b7581d6d2",
)

jvm_maven_import_external(
    name = "junit_junit",
    server_urls = ["http://central.maven.org/maven2"],
    licenses = ["notice"], # Apache 2.0
    artifact = "junit:junit:4.12",
    artifact_sha256 = "59721f0805e223d84b90677887d9ff567dc534d7c502ca903c0c2b17f05c116a",
)

jvm_maven_import_external(
    name = "javax_inject_javax_inject",
    server_urls = ["http://central.maven.org/maven2"],
    licenses = ["notice"], # Apache 2.0
    artifact = "javax.inject:javax.inject:1",
    artifact_sha256 = "91c77044a50c481636c32d916fd89c9118a72195390452c81065080f957de7ff",
)

jvm_maven_import_external(
    name = "org_hamcrest_java_hamcrest",
    server_urls = ["http://central.maven.org/maven2"],
    licenses = ["notice"], # Apache 2.0
    artifact = "org.hamcrest:java-hamcrest:2.0.0.0",
    artifact_sha256 = "09bc7044d57a497846e2480250e7a72ff3ae58efefc8c3a9ceecd0f4e092851c",
)

jvm_maven_import_external(
    name = "com_google_guava_guava",
    server_urls = ["http://central.maven.org/maven2"],
    licenses = ["notice"], # Apache 2.0
    artifact = "com.google.guava:guava:26.0-android",
    artifact_sha256 = "1d044ebb866ef08b7d04e998b4260c9b52fab6e6d6b68d207859486bb3686cd5",
)

jvm_maven_import_external(
    name = "truth",
    server_urls = ["http://central.maven.org/maven2"],
    licenses = ["notice"], # Apache 2.0
    artifact = "com.google.truth:truth:0.42",
    artifact_sha256 = "dd652bdf0c4427c59848ac0340fd6b6d20c2cbfaa3c569a8366604dbcda5214c",
)
