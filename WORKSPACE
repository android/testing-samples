# Set the path to your local SDK installation, or use the ANDROID_HOME environment variable.
android_sdk_repository(
    name = "androidsdk",
    # path = "/path/to/sdk",
)

# Android Test Support
ATS_COMMIT = "be7fa5191b3e3eb70411357af13f011dcfb95b50"

http_archive(
    name = "android_test_support",
    strip_prefix = "android-testing-support-library-%s" % ATS_COMMIT,
    urls = ["https://github.com/google/android-testing-support-library/archive/%s.tar.gz" % ATS_COMMIT],
)

load("@android_test_support//:repo.bzl", "android_test_repositories")

android_test_repositories()

# Google Maven Repository
GMAVEN_COMMIT = "44d75d3e7bdfa8ff0b30ceb048b0f09bc6b72c70"

http_archive(
    name = "gmaven_rules",
    strip_prefix = "gmaven_rules-%s" % GMAVEN_COMMIT,
    urls = ["https://github.com/aj-michael/gmaven_rules/archive/%s.tar.gz" % GMAVEN_COMMIT],
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
    artifact = "com.google.guava:guava:18.0",
)
