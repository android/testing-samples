#!/bin/bash

# Simple script to update the androidx.test versions used in this project.

set -ex  # Exit immediately if a command exits with a non-zero status.

#repourl="" # Leave empty to be ignored
#repourl="file:\/\/\~/test_repo\/m2repository"
#repourl="https:\/\/oss.sonatype.org\/content\/repositories\/orgrobolectric-1216"
#repourl="http:\/\/localhost:1480"
buildToolsVersion="29.0.3"
androidxAnnotationVersion="1.1.0"
androidxCoreVersion="1.2.0"
androidxRecyclerVersion="1.1.0"
guavaVersion="29.0-android"
compileSdkVersion="29"
targetSdkVersion="29"
runnerVersion="1.3.0-beta02"
rulesVersion="1.3.0-beta02"
coreVersion="1.3.0-beta02"
extJUnitVersion="1.1.2-beta02"
extTruthVersion="1.3.0-beta02"
espressoVersion="3.3.0-beta02"
robolectricVersion="4.3.1"
uiautomatorVersion="2.2.0"

for p in $(cat projects.conf); do
   echo
   echo
   echo Updating version in $p
   echo "====================================================================="
   pushd $p > /dev/null  # Silent pushd

   # Replace versions
   sed -i "s/coreVersion = \".*\"/coreVersion = \"$coreVersion\"/" build.gradle
   sed -i "s/extJUnitVersion = \".*\"/extJUnitVersion = \"$extJUnitVersion\"/" build.gradle
   sed -i "s/extTruthVersion = \".*\"/extTruthVersion = \"$extTruthVersion\"/" build.gradle
   sed -i "s/runnerVersion = \".*\"/runnerVersion = \"$runnerVersion\"/" build.gradle
   sed -i "s/rulesVersion = \".*\"/rulesVersion = \"$rulesVersion\"/" build.gradle
   sed -i "s/espressoVersion = \".*\"/espressoVersion = \"$espressoVersion\"/" build.gradle
   sed -i "s/buildToolsVersion = \".*\"/buildToolsVersion = \"$buildToolsVersion\"/" build.gradle
   sed -i "s/androidxAnnotationVersion = \".*\"/androidxAnnotationVersion = \"$androidxAnnotationVersion\"/" build.gradle
   sed -i "s/androidxCoreVersion = \".*\"/androidxCoreVersion = \"$androidxCoreVersion\"/" build.gradle
   sed -i "s/androidxRecyclerVersion = \".*\"/androidxRecyclerVersion = \"$androidxRecyclerVersion\"/" build.gradle
   sed -i "s/guavaVersion = \".*\"/guavaVersion = \"$guavaVersion\"/" build.gradle
   sed -i "s/compileSdkVersion .*/compileSdkVersion $compileSdkVersion/" app/build.gradle
   sed -i "s/targetSdkVersion .*/targetSdkVersion $targetSdkVersion/" app/build.gradle
   sed -i "s/uiautomatorVersion = \".*\"/uiautomatorVersion = \"$uiautomatorVersion\"/" build.gradle
   sed -i "s/robolectricVersion = \".*\"/robolectricVersion = \"$robolectricVersion\"/" build.gradle
   if [ ! -z "$repourl" ]
   then
         sed -i "s/.*jcenter()/        jcenter()\n        maven {\n            url \"$repourl\"\n        }/" build.gradle
   fi
   popd > /dev/null  # Silent popd
  done
echo
echo "All Converted"

