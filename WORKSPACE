# Set the paths to your local SDK and NDK installations

SDK_PATH = "/path/to/sdk"

android_sdk_repository(
    name = "androidsdk",
    path = SDK_PATH,
)

git_repository(
    name = "android_test_support",
    commit = "2ca4679c303ed66fff4411de0606f63ae26123dd",
    remote = "https://github.com/google/android-testing-support-library",
)

load("@android_test_support//:repo.bzl", "android_test_repositories")

android_test_repositories()

git_repository(
    name = "gmaven_rules",
    commit = "5e89b7cdc94d002c13576fad3b28b0ae30296e55",
    remote = "https://github.com/aj-michael/gmaven_rules",
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
