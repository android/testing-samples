#!/bin/bash

# Simple script to update the androidx.test versions used in this project.

set -ex  # Exit immediately if a command exits with a non-zero status.

#repourl="" # Leave empty to be ignored
#repourl="file:\/\/\/usr\/local\/company\/home\/user\/test_repos\/1.4.1-alpha02\/m2repository"
#repourl="https:\/\/oss.sonatype.org\/content\/repositories\/orgrobolectric-1216"
#repourl="http:\/\/localhost:1480"
buildToolsVersion="31.0.0"
agpVersion="7.0.2"
kotlinVersion="1.4.31"
compileSdkVersion="30"
targetSdkVersion="30"
androidxAnnotationVersion="1.2.0"
androidxCompatVersion="1.3.1"
androidxCoreVersion="1.6.0"
androidxFragmentVersion="1.3.6"
androidxRecyclerVersion="1.2.1"
guavaVersion="30.1.1-android"
truthVersion="1.1.3"
runnerVersion="1.4.1-alpha02"
rulesVersion="1.4.1-alpha02"
coreVersion="1.4.1-alpha02"
extJUnitVersion="1.1.4-alpha02"
extTruthVersion="1.5.0-alpha02"
espressoVersion="3.5.0-alpha02"
robolectricVersion="4.6.1"
uiAutomatorVersion="2.2.0"

for p in $(cat projects.conf); do
   echo
   echo
   echo Updating version in $p
   echo "====================================================================="
   pushd $p > /dev/null  # Silent pushd

   # Replace versions
   sed -i "s/buildToolsVersion = \".*\"/buildToolsVersion = \"$buildToolsVersion\"/" build.gradle
   sed -i "s/agpVersion = \".*\"/agpVersion = \"$agpVersion\"/" build.gradle
   sed -i "s/kotlinVersion = \".*\"/kotlinVersion = \"$kotlinVersion\"/" build.gradle
   sed -i "s/coreVersion = \".*\"/coreVersion = \"$coreVersion\"/" build.gradle
   sed -i "s/extJUnitVersion = \".*\"/extJUnitVersion = \"$extJUnitVersion\"/" build.gradle
   sed -i "s/extTruthVersion = \".*\"/extTruthVersion = \"$extTruthVersion\"/" build.gradle
   sed -i "s/runnerVersion = \".*\"/runnerVersion = \"$runnerVersion\"/" build.gradle
   sed -i "s/rulesVersion = \".*\"/rulesVersion = \"$rulesVersion\"/" build.gradle
   sed -i "s/espressoVersion = \".*\"/espressoVersion = \"$espressoVersion\"/" build.gradle
   sed -i "s/androidxAnnotationVersion = \".*\"/androidxAnnotationVersion = \"$androidxAnnotationVersion\"/" build.gradle
   sed -i "s/androidxCompatVersion = \".*\"/androidxCompatVersion = \"$androidxCompatVersion\"/" build.gradle
   sed -i "s/androidxCoreVersion = \".*\"/androidxCoreVersion = \"$androidxCoreVersion\"/" build.gradle
   sed -i "s/androidxFragmentVersion = \".*\"/androidxFragmentVersion = \"$androidxFragmentVersion\"/" build.gradle
   sed -i "s/androidxRecyclerVersion = \".*\"/androidxRecyclerVersion = \"$androidxRecyclerVersion\"/" build.gradle
   sed -i "s/guavaVersion = \".*\"/guavaVersion = \"$guavaVersion\"/" build.gradle
   sed -i "s/truthVersion = \".*\"/truthVersion = \"$truthVersion\"/" build.gradle
   sed -i "s/compileSdkVersion .*/compileSdkVersion $compileSdkVersion/" app/build.gradle
   sed -i "s/targetSdkVersion .*/targetSdkVersion $targetSdkVersion/" app/build.gradle
   sed -i "s/uiAutomatorVersion = \".*\"/uiAutomatorVersion = \"$uiAutomatorVersion\"/" build.gradle
   sed -i "s/robolectricVersion = \".*\"/robolectricVersion = \"$robolectricVersion\"/" build.gradle
   if [ ! -z "$repourl" ]
   then
         sed -i "s/.*google()/        google()\n        maven {\n            url \"$repourl\"\n          allowInsecureProtocol=true\n        }/" build.gradle
   fi
   popd > /dev/null  # Silent popd
  done
echo
echo "All Converted"

