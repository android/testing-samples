cmake_minimum_required(VERSION 3.10.2)

project(junit-gtest-example LANGUAGES CXX)

include_directories(include)

add_library(adder SHARED src/adder.cpp)

if (ENABLE_NATIVE_TEST)
    find_package(googletest REQUIRED CONFIG)
    find_package(junit-gtest REQUIRED CONFIG)

    add_library(adder-test SHARED test/adder_test.cpp)

    target_link_libraries(adder-test
        PRIVATE
        adder
        googletest::gtest
        junit-gtest::junit-gtest
    )
endif()