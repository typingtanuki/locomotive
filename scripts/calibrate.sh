#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source "${scriptDir}/common.sh"
parseArguments "$@"
setupLogFile "calibration"

welcome "This wizard will help you calibrate your touch screen"

installTool "xinput-calibrator" "Xinput calibrator" "xinput_calibrator"

# File with the raw output of xinput-calibrator
calibratorOutput="${scriptDir}/calibrator_output.txt.tmp"
# File with only the "Section" ... "EndSection" part of the file
calibrationTmp="${scriptDir}/calibration.conf.tmp"

# Run xinput-calibrator
xinput_calibrator | tee "${calibratorOutput}" >> "${logFile}" 2>&1

# Extract the "Section" ... "EndSection" part of the file
output=$(grep -A 1000 "Section" "${calibratorOutput}" | grep -B 1000 "EndSection" | grep -v "MatchProduct")
outputLength=$(echo "${output}" | wc -l)

if [[ "${outputLength}" -gt 2 ]];
then
  # It seems to have worked, let's make the change persistent
  echo "${output}" > "${calibrationTmp}"
  sudo mkdir -p /etc/X11/xorg.conf.d
  sudo chmod a+r "${calibrationTmp}"
  sudo mv "${calibrationTmp}" /etc/X11/xorg.conf.d/99-calibration.conf
else
  # It seems to have failed, let's also fail
  echo "Calibration failed, output is invalid"
  cat "${calibratorOutput}"
  exit 1
fi