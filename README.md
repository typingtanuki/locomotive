# locomotive

## Installing

1. Install git

```bash
sudo apt install git
```

2. Clone repository

```bash
cd ~
git clone git@github.com:typingtanuki/locomotive.git .locomotive
```

3. Setup the extra PPAs and update libraries

```bash
~/.locomotive/scripts/ppa.sh
```

4. Install other libraries

```bash
~/.locomotive/scripts/setup.sh
```

## Updating

```bash
cd ~/.locomotive
git pull --rebase
```
