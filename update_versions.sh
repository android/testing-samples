#!/bin/bash

# Simple script to update the androidx.test versions used in this project.

set -ex  # Exit immediately if a command exits with a non-zero status.

#repourl="" # Leave empty to be ignored
#repourl="file:\/\/\/tmp\/test_repo\/m2repository"
#repourl="https:\/\/oss.sonatype.org\/content\/repositories\/orgrobolectric-1216"
#repourl="http:\/\/localhost:1480"
# Versions:                             # axt_versions.bzl equivalents
buildToolsVersion="32.0.0"
agpVersion="7.3.1"
kotlinVersion="1.7.10"                  # KOTLIN_VERSION
compileSdkVersion="33"
targetSdkVersion="33"
androidxAnnotationVersion="1.5.0"       # ANDROIDX_ANNOTATION_VERSION
androidxCompatVersion="1.5.1"           # ANDROIDX_COMPAT_VERSION
androidxCoreVersion="1.9.0"             # ANDROIDX_CORE_VERSION
androidxFragmentVersion="1.5.3"         # ANDROIDX_FRAGMENT_VERSION
androidxRecyclerVersion="1.2.1"         # ANDROIDX_RECYCLERVIEW_VERSION
guavaVersion="31.1-android"           # GUAVA_VERSION
truthVersion="1.1.3"                    # TRUTH_VERSION
runnerVersion="1.5.0-beta02"           # RUNNER_VERSION
monitorVersion="1.6.0-beta01"          # MONITOR_VERSION
rulesVersion="1.5.0-beta01"            # RULES_VERSION
servicesVersion="1.4.2-beta01"         # SERVICES_VERSION
orchestratorVersion="1.4.2-beta01"     # ORCHESTRATOR_VERSION
coreVersion="1.5.0-beta01"             # CORE_VERSION
extJUnitVersion="1.1.4-beta01"         # ANDROIDX_JUNIT_VERSION
extTruthVersion="1.5.0-beta02"         # ANDROIDX_TRUTH_VERSION
espressoVersion="3.5.0-beta02"         # ESPRESSO_VERSION
espressoDeviceVersion="1.0.0-alpha01"  # ESPRESSO_DEVICE_VERSION
robolectricVersion="4.9"
uiAutomatorVersion="2.2.0"              # UIAUTOMATOR_VERSION

for p in $(cat projects.conf); do
   echo
   echo
   echo Updating version in $p
   echo "====================================================================="
   pushd $p > /dev/null  # Silent pushd

   # Replace versions
   sed -i "s/buildToolsVersion = \([\"']\).*\1/buildToolsVersion = \"$buildToolsVersion\"/" build.gradle
   sed -i "s/agpVersion = \([\"']\).*\1/agpVersion = \"$agpVersion\"/" build.gradle
   sed -i "s/kotlinVersion = \([\"']\).*\1/kotlinVersion = \"$kotlinVersion\"/" build.gradle
   sed -i "s/coreVersion = \([\"']\).*\1/coreVersion = \"$coreVersion\"/" build.gradle
   sed -i "s/extJUnitVersion = \([\"']\).*\1/extJUnitVersion = \"$extJUnitVersion\"/" build.gradle
   sed -i "s/extTruthVersion = \([\"']\).*\1/extTruthVersion = \"$extTruthVersion\"/" build.gradle
   sed -i "s/monitorVersion = \([\"']\).*\1/monitorVersion = \"$monitorVersion\"/" build.gradle
   sed -i "s/runnerVersion = \([\"']\).*\1/runnerVersion = \"$runnerVersion\"/" build.gradle
   sed -i "s/rulesVersion = \([\"']\).*\1/rulesVersion = \"$rulesVersion\"/" build.gradle
   sed -i "s/servicesVersion = \([\"']\).*\1/servicesVersion = \"$servicesVersion\"/" build.gradle
   sed -i "s/orchestratorVersion = \([\"']\).*\1/orchestratorVersion = \"$orchestratorVersion\"/" build.gradle
   sed -i "s/espressoVersion = \([\"']\).*\1/espressoVersion = \"$espressoVersion\"/" build.gradle
   sed -i "s/androidxAnnotationVersion = \([\"']\).*\1/androidxAnnotationVersion = \"$androidxAnnotationVersion\"/" build.gradle
   sed -i "s/androidxCompatVersion = \([\"']\).*\1/androidxCompatVersion = \"$androidxCompatVersion\"/" build.gradle
   sed -i "s/androidxCoreVersion = \([\"']\).*\1/androidxCoreVersion = \"$androidxCoreVersion\"/" build.gradle
   sed -i "s/androidxFragmentVersion = \([\"']\).*\1/androidxFragmentVersion = \"$androidxFragmentVersion\"/" build.gradle
   sed -i "s/androidxRecyclerVersion = \([\"']\).*\1/androidxRecyclerVersion = \"$androidxRecyclerVersion\"/" build.gradle
   sed -i "s/guavaVersion = \([\"']\).*\1/guavaVersion = \"$guavaVersion\"/" build.gradle
   sed -i "s/truthVersion = \([\"']\).*\1/truthVersion = \"$truthVersion\"/" build.gradle
   sed -i "s/compileSdkVersion .*/compileSdkVersion $compileSdkVersion/" app/build.gradle
   sed -i "s/targetSdkVersion .*/targetSdkVersion $targetSdkVersion/" app/build.gradle
   sed -i "s/uiAutomatorVersion = \([\"']\).*\1/uiAutomatorVersion = \"$uiAutomatorVersion\"/" build.gradle
   sed -i "s/robolectricVersion = \([\"']\).*\1/robolectricVersion = \"$robolectricVersion\"/" build.gradle
   if [ ! -z "$repourl" ]
   then
      for G in build.gradle settings.gradle; do
          sed -i "s/.*google()/        google()\n        maven {\n            url \"$repourl\"\n          allowInsecureProtocol=true\n        }/" $G
      done
   fi
   popd > /dev/null  # Silent popd
  done
echo
echo "All Converted"

