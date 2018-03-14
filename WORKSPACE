# Set the path to your local SDK installation

SDK_PATH = "/path/to/sdk"

android_sdk_repository(
    name = "androidsdk",
    path = SDK_PATH,
)

git_repository(
    name = "android_test_support",
    commit = "8bd3b317897c54f8a91d22065880002a0a68e70e",
    remote = "https://github.com/google/android-testing-support-library",
)

load("@android_test_support//:repo.bzl", "android_test_repositories")

android_test_repositories()

git_repository(
    name = "gmaven_rules",
    commit = "ecc3a8fc236ad89fe6511feb743d1b08be1b53c9",
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
