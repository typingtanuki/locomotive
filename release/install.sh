#!/bin/bash

set -e

scriptDir=$(dirname "$(readlink -f $0)")
cd "${scriptDir}"
java -jar locomotive.jar
