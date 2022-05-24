#include "adder"
#include "gtest/gtest.h"

TEST(adder, pass) {
    ASSERT_EQ(42, add(40, 2));
}

TEST(foo, fail) {
    ASSERT_EQ(-42, add(-40, -2));
}