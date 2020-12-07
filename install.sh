#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source ${scriptDir}/scripts/common.sh
parseArguments "$@"

${scriptDir}/scripts/ppa.sh
${scriptDir}/scripts/setup.sh

