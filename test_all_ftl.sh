#!/bin/bash

set -e  # Exit immediately if a command exits with a non-zero status.

for line in $(find . -name 'gradlew'); do
   p=$(dirname "${line}");
   name="${p##*/}";
   echo
   echo
   echo Running unit and Android tests in $name
   echo "====================================================================="

   pushd $p > /dev/null  # Silent pushd
   ./gradlew :app:assembleDebug -PdisablePreDex | sed "s@^@$name @"  # Prefix every line with directory
   code=${PIPESTATUS[0]}
   if [ "$code" -ne "0" ]; then
       exit $code
   fi

   ./gradlew :app:assembleAndroidTest -PdisablePreDex | sed "s@^@$name @"  # Prefix every line with directory
   ./gradlew test -PdisablePreDex | sed "s@^@$name @"  # Prefix every line with directory

   apkfile=app/build/outputs/apk/debug/app-debug.apk
   testapkfile=app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
   if [ ! -f $apkfile ] || [ ! -f $testapkfile ] ; then
      echo "APKs not found, probably due to project using multiple flavors. Skipping $name"
      popd > /dev/null  # Silent popd
      continue
   fi
   echo "Sending APKs to Firebase..."
   echo "y" | sudo /opt/google-cloud-sdk/bin/gcloud firebase test android run --app $apkfile --test $testapkfile -d Nexus5X -v 26 -l fr --results-bucket=android-testing-samples-test-results --results-dir=$CIRCLE_BUILD_NUM/$name

   code=${PIPESTATUS[0]}
   if [ "$code" -ne "0" ]; then
       exit $code
   fi

   # Copy the test results to the build artifacts folder in CircleCI
   sudo /opt/google-cloud-sdk/bin/gsutil -m cp -r -U `sudo /opt/google-cloud-sdk/bin/gsutil ls gs://android-testing-samples-test-results/$CIRCLE_BUILD_NUM/$name/**/test_result*.xml | tail -1` $CIRCLE_ARTIFACTS/ | true
   popd > /dev/null  # Silent popd
done

echo
echo "ALL TESTS PASS"
