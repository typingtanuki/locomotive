#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source ${scriptDir}/common.sh
parseArguments "$@"

welcome "This wizard will help you calibrate your touch screen"

installTool "xinput-calibrator" "Xinput calibrator" "xinput_calibrator"

xinput_calibrator

echo ""
echo "Copy/paste the output in the following file:"
echo "/etc/X11/xorg.conf.d/99-calibration.conf"