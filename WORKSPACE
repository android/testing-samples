# Set the path to your local SDK installation, or use the ANDROID_HOME environment variable.
android_sdk_repository(
    name = "androidsdk",
    api_level = 28,
    build_tools_version = "28.0.2",
    # path = "/path/to/sdk",
)

# Android Test Support
ATS_COMMIT = "e39a8c7769a5c8b498d0deb0deef3a25b289d410"

http_archive(
    name = "android_test_support",
    strip_prefix = "android-test-%s" % ATS_COMMIT,
    urls = ["https://github.com/android/android-test/archive/%s.tar.gz" % ATS_COMMIT],
)

load("@android_test_support//:repo.bzl", "android_test_repositories")

android_test_repositories()

# Google Maven Repository
GMAVEN_TAG = "20181010-1"

http_archive(
    name = "gmaven_rules",
    strip_prefix = "gmaven_rules-%s" % GMAVEN_TAG,
    url = "https://github.com/bazelbuild/gmaven_rules/archive/%s.tar.gz" % GMAVEN_TAG,
)

load("@gmaven_rules//:gmaven.bzl", "gmaven_rules")

gmaven_rules()


maven_jar(
    name = "com_google_inject_guice",
    artifact = "com.google.inject:guice:4.0",
)

maven_jar(
    name = "junit_junit",
    artifact = "junit:junit:4.12",
)

maven_jar(
    name = "javax_inject_javax_inject",
    artifact = "javax.inject:javax.inject:1",
)

maven_jar(
    name = "org_hamcrest_java_hamcrest",
    artifact = "org.hamcrest:java-hamcrest:2.0.0.0",
)

maven_jar(
    name = "com_google_guava_guava",
    artifact = "com.google.guava:guava:26.0-android",
)

maven_jar(
    name = "truth",
    artifact = "com.google.truth:truth:0.42",
)
