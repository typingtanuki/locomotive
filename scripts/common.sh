function questionRaw {
  # Utility to ask user a yes/no question
  #
  # Arguments:
  # $1: The question to ask

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
    return 0;
  else
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


  checkSudo
  if [[ $(sudo apt install "$@" -y 2>&1) ]]
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
  installedPpas=$(apt policy 2>&1)
  installed=$(echo "${installedPpas}" | grep "$ppa")

  if [ -z "$installed" ]
  then
    return 1;
  else
    return 0;
  fi
}

function checkSudo {
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

function title {
  # Prints a title
  #
  # Arguments:
  # $1 The title to print

  fullTitle="Locomotive: $1"
  columns=$(tput cols)
  titleLength=$(expr length "${fullTitle}")
  padding=$((($columns - $titleLength)/2))
  pad=$(printf "%-${padding}s" " ")
  full=$(printf "%-${columns}s" " " | tr ' ' '=')

  echo "${full}"
  echo "${pad}${fullTitle}"
  echo "${full}"
}

function subtitle {
  # Prints a subtitle
  #
  # Arguments:
  # $1 The subtitle to print

  fullTitle="-- $1 --"
  columns=$(tput cols)
  titleLength=$(expr length "${fullTitle}")
  padding=$((($columns - $titleLength)/2))
  pad=$(printf "%-${padding}s" " ")

  echo "${pad}${fullTitle}"
  echo ""
}

function installedPpaKey {
  installed=$(apt-key list 2>/dev/null | grep "$1")

  if [ -z "$installed" ]
  then
    return 1;
  else
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

  echo "Fetching updates..."
  checkSudo
  output=$(sudo apt update 2>&1)
  echo "Upgrading step 1/2..."
  checkSudo
  output=$(sudo apt upgrade -y -f 2>&1)
  echo "Upgrading step 2/2..."
  checkSudo
  output=$(sudo apt dist-upgrade -y -f 2>&1)
  echo "Removing old dependencies..."
  checkSudo
  output=$(sudo apt autoremove -y 2>&1)
  echo "Cleaning up cache..."
  checkSudo
  output=$(sudo apt autoclean -y 2>&1)
}