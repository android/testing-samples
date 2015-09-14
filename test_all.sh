#!/bin/bash

set -e  # Exit immediately if a command exits with a non-zero status.

for p in $(cat projects.conf); do
   echo
   echo
   echo Running unit and Android tests in $p
   echo "====================================================================="

   pushd $p > /dev/null  # Silent pushd
   ./gradlew $@ test cAT | sed "s@^@$p @"  # Prefix every line with directory
   code=${PIPESTATUS[0]}
   if [ "$code" -ne "0" ]; then
       exit $code
   fi
   popd > /dev/null  # Silent popd
done

echo
echo "ALL TESTS PASS"
