# Argument for hiding the welcome screens
disableWelcome=0
# Argument for answering "yes" to all questions
allYes=0

function parseArguments {
  # Parse arguments
  #
  # Arguments:
  #  All arguments to the main script

  while [ -n "$1" ];
  do
    if [[ "$1" == "--help" ]] || [[ "$1" == "-h" ]];
    then
      showUsage 0
      shift
    elif [[ "$1" == "--no-welcome" ]] || [[ "$1" == "-w" ]];
    then
      disableWelcome=1
      shift
    elif [[ "$1" == "--all-yes" ]] || [[ "$1" == "-y" ]];
    then
      allYes=1
      shift
    else
      echo "Unknown argument ${1}"
      showUsage 1
      shift
    fi
  done
}

function showUsage {
  # Show the command line arguments
  #
  # Arguments: The exit code to use

  echo "Arguments:"
  echo "  --help -h: Show this help"
  echo "  --no-welcome -w: Hide the welcome page"
  echo "  --all-yes -y: Answer 'yes' to all questions"
  exit "$1"
}


# The folder into which to save the logs
logFolder="${scriptDir}/../log"
# Make sure the log folder exists
mkdir -p "${logFolder}"
# The default log file (Nothing should be written here if everything goes fine)
logFile="${logFolder}/locomotive.log"

function setupLogFile {
  # Setup the log files for the current script
  #
  # Arguments:
  # $1: The name of the script

  logFile="${logFolder}/locomotive-${1}.log"
  if [ -f "${logFile}" ];
  then
    mv "${logFile}" "${logFile}.old"
  fi
  datetime >> "${logFile}"
}

function questionRaw {
  # Utility to ask user a yes/no question
  #
  # Arguments:
  # $1: The question to ask

  if [ "${allYes}" -eq "1" ];
  then
    return 0;
  fi

  while true; do
    read -p "$1 ? ([y]es/[n]o): " yn
    case $yn in
      [Yy]* ) return 0;;
      "" ) return 0;;
      [Nn]* ) return 1;;
      * ) echo "Please answer [y]es or [n]o.";;
    esac
  done
}

function questionInstall {
  # Ask user if they want to install a given library or not
  #
  # Arguments:
  # $1: The name of the package to ask about

  if questionRaw "Do you wish to install $1";
  then
    return 0
  else
    return 1
  fi
}

function questionInstallPpa {
  # Ask user if they want to install a given PPA or not
  #
  # Arguments:
  # $1: The name of the PPA to ask about

  if questionRaw "Do you wish to install PPA $1";
  then
    return 0
  else
    return 1
  fi
}

function questionInstallKey {
  # Ask user if they want to install a given PPA key or not
  #
  # Arguments:
  # $1: The name of the key to ask about

  if questionRaw "Do you wish to install PPA key for $1";
  then
    return 0
  else
    return 1
  fi
}

function installed {
  # Check if binary is available
  #
  # Arguments:
  # $1: The binary to check for existance

  if [[ $(type -P "$1") ]]
  then
    echo "$1 is installed" >> "${logFile}"
    return 0;
  else
    echo "$1 is not installed" >> "${logFile}"
    return 1;
  fi
}

function installTool {
  # Install package from APT if it is not already installed
  #
  # Arguments:
  # $1: APT package to install
  # $2: Display name (optional)
  # $3: Binary to check for package existence (optional)

  package=$1
  if [ -z "$2" ]
  then
    name=$1
  else
    name=$2
  fi

  if [ -z "$3" ]
  then
    binary=$1
  else
    binary=$3
  fi

  if installed "$binary";
  then
    echo "$name already installed"
  else
    if questionInstall "$name";
    then
      aptInstall "$package"
    fi
  fi
}

function aptInstall {
  # Install package(s) from APT
  #
  # Arguments:
  # All packages to install (can also be arguments for "apt install" command)

  echo "Installing $* with APT" >> "${logFile}"
  checkSudo
  if [[ $(sudo apt install "$@" -y >> "${logFile}" 2>&1) ]]
  then
    echo "OK"
  else
    echo "ERROR"
  fi
}

function installedPpa {
  # Check if PPA is already installed
  #
  # Arguments:
  # $1: The PPA to check for existence

  ppa=$(echo "${1}" | cut -d ':' -f 2 | cut -d ' ' -f 1)
  installedPpas=$(apt policy  >> "${logFile}" 2>&1)
  installed=$(echo "${installedPpas}" | grep "$ppa")

  if [ -z "$installed" ]
  then
    echo "PPA $1 is not installed" >> "${logFile}"
    return 1;
  else
    echo "PPA $1 is installed" >> "${logFile}"
    return 0;
  fi
}

function checkSudo {
  # Make sure sudo is enabled, before we start redirecting output to files
  #
  # Arguments: None

  sudo echo -n ""
}

function ppaInstall {
  # Add PPA to APT
  #
  # Arguments:
  # $1 the PPA to add

  if installedPpa "$1";
  then
    echo "$1 already installed"
  else
    if questionInstallPpa "$1";
    then
      echo "Adding PPA $1" >> "${logFile}"
      checkSudo
      if [[ $(sudo apt-add-repository "$1" -y 2>&1) ]]
      then
        echo "OK"
      else
        echo "ERROR"
      fi
    fi
  fi
}

function centerText {
  # Prints the text in the center of the screen
  #
  # Arguments:
  # $1 The string to print

  columns=$(tput cols)
  titleLength=$(echo "${1}" | wc -l)
  padding=$(((columns - titleLength)/2))
  pad=$(printf "%-${padding}s" " ")
  echo "${pad}${1}"
}

function drawLine {
  # Draws a line covering the entire screen width
  #
  # Arguments:
  # $1 The character to use for the line

  columns=$(tput cols)
  printf "%-${columns}s" " " | tr ' ' "${1}"
}

function welcome {
  if [ "${disableWelcome}" -eq "1" ];
  then
    return;
  fi

  centered1=$(centerText ".____                                       __  .__              ")
  centered2=$(centerText "|    |    ____   ____  ____   _____   _____/  |_|__|__  __ ____  ")
  centered3=$(centerText "|    |   /  _ \\_/ ___\\/  _ \\ /     \\ /  _ \\   __\\  \\  \\/ // __ \\ ")
  centered4=$(centerText "|    |__(  <_> )  \\__(  <_> )  Y Y  (  <_> )  | |  |\\   /\\  ___/ ")
  centered5=$(centerText "|_______ \\____/ \\___  >____/|__|_|  /\\____/|__| |__| \\_/  \\___  >")
  centered6=$(centerText "        \\/          \\/            \\/                          \\/ ")

  centered7=$(centerText "${1}")
  centered8=$(centerText "${2}")
  full=$(drawLine "=")

  echo "${full}"
  echo "${centered1}"
  echo "${centered2}"
  echo "${centered3}"
  echo "${centered4}"
  echo "${centered5}"
  echo "${centered6}"
  echo ""
  echo "${centered7}"
  echo "${centered8}"
  echo "${full}"
  read -p "Press [ENTER] to start."
}

function title {
  # Prints a title
  #
  # Arguments:
  # $1 The title to print

  centered=$(centerText "${1}")
  full=$(drawLine "=")

  echo "${full}"
  echo "${centered}"
  echo "${full}"
}

function subtitle {
  # Prints a subtitle
  #
  # Arguments:
  # $1 The subtitle to print

  centered=$(centerText "-- ${1} --")
  echo "${centered}"
  echo ""
}

function installedPpaKey {
  installed=$(apt-key list 2>/dev/null | grep "$1")

  if [ -z "$installed" ]
  then
    echo "PPA key $1 is not installed" >> "${logFile}" 2>&1
    return 1;
  else
    echo "PPA key $1 is installed" >> "${logFile}" 2>&1
    return 0;
  fi
}

function ppaAddKey {
  # Add key for PPA
  #
  # Arguments:
  # $1 The name of the key
  # $2 The key to import

  if installedPpaKey "$1";
  then
    echo "Key for $1 already installed"
  else
    if questionInstallKey "$1";
    then
      echo "Adding PPA key $1" >> "${logFile}" 2>&1
      checkSudo
      if [[ $(wget -O - "${2}" | sudo apt-key add - 2>&1) ]]
      then
        echo "OK"
      else
        echo "ERROR"
      fi
    fi
  fi
}

function aptUpdate {
  # Update APT cache and upgrade system
  #
  # Arguments: None

  echo "Update APT" >> "${logFile}" 2>&1

  echo "Fetching updates..."
  checkSudo
  sudo apt update >> "${logFile}" 2>&1
  echo "Upgrading step 1/2..."
  checkSudo
  sudo apt upgrade -y -f >> "${logFile}" 2>&1
  echo "Upgrading step 2/2..."
  checkSudo
  sudo apt dist-upgrade -y -f >> "${logFile}" 2>&1
  echo "Removing old dependencies..."
  checkSudo
  sudo apt autoremove -y >> "${logFile}" 2>&1
  echo "Cleaning up cache..."
  checkSudo
  sudo apt autoclean -y >> "${logFile}" 2>&1
}
