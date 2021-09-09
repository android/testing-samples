#!/bin/bash

# Simple script to update the androidx.test versions used in this project.

set -ex  # Exit immediately if a command exits with a non-zero status.

#repourl="" # Leave empty to be ignored
#repourl="file:\/\/\/~\/test_repos\/1.4.1-alpha01\/m2repository"
#repourl="https:\/\/oss.sonatype.org\/content\/repositories\/orgrobolectric-1216"
#repourl="http:\/\/localhost:1480"
buildToolsVersion="30.0.2"
compileSdkVersion="30"
targetSdkVersion="30"
androidxAnnotationVersion="1.1.0"
androidxCoreVersion="1.2.0"
androidxRecyclerVersion="1.1.0"
guavaVersion="29.0-android"
truthVersion="1.0.1"
runnerVersion="1.4.1-alpha01"
rulesVersion="1.4.1-alpha01"
coreVersion="1.4.1-alpha01"
extJUnitVersion="1.1.4-alpha01"
extTruthVersion="1.5.0-alpha01"
espressoVersion="3.5.0-alpha01"
robolectricVersion="4.5.1"
uiAutomatorVersion="2.2.0"

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
   sed -i "s/truthVersion = \".*\"/truthVersion = \"$truthVersion\"/" build.gradle
   sed -i "s/compileSdkVersion .*/compileSdkVersion $compileSdkVersion/" app/build.gradle
   sed -i "s/targetSdkVersion .*/targetSdkVersion $targetSdkVersion/" app/build.gradle
   sed -i "s/uiAutomatorVersion = \".*\"/uiAutomatorVersion = \"$uiAutomatorVersion\"/" build.gradle
   sed -i "s/robolectricVersion = \".*\"/robolectricVersion = \"$robolectricVersion\"/" build.gradle
   if [ ! -z "$repourl" ]
   then
         sed -i "s/.*jcenter()/        jcenter()\n        maven {\n            url \"$repourl\"\n        }/" build.gradle
   fi
   popd > /dev/null  # Silent popd
  done
echo
echo "All Converted"

