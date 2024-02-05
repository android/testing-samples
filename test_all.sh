#!/bin/bash

set -e  # Exit immediately if a command exits with a non-zero status.

for p in $(cat projects.conf); do
   echo
   echo
   echo Running unit and Android tests in $p
   echo "====================================================================="

   pushd $p > /dev/null  # Silent pushd

   ./gradlew $@ testDebug | sed "s@^@$p @"  # Prefix every line with directory

   if [ "$p" == "unit/BasicNativeAndroidTest" ]; then
       ./gradlew $@ nexusOneApi30CoreDebugAndroidTest --info | sed "s@^@$p @"  # Prefix every line with directory
       ./gradlew $@ nexusOneApi30NativeTestDebugAndroidTest --info | sed "s@^@$p @"
   else
       ./gradlew $@ nexusOneApi30DebugAndroidTest --info | sed "s@^@$p @"  # Prefix every line with directory
   fi

   code=${PIPESTATUS[0]}
   if [ "$code" -ne "0" ]; then
       exit $code
   fi
   popd > /dev/null  # Silent popd
done

echo
echo "ALL TESTS PASS"
