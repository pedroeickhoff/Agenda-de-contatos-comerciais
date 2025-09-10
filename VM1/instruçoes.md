```bash
# 1. Para instalar o nginx corretamente primeiramente atualize os pacotes:
sudo apt update && sudo apt upgrade -y

# 2. Depois realize a instalação do nginx:

sudo apt install nginx -y

# 3. Após isso acesse o arquivo de configuração e deixe igual ao nginx.conf, substituindo o ip do proxy_pass para o seu ip que deseja redirecionar, no caso da minha máquina o ip da aplicação é http://192.168.19.128:8080/, aperte control o, depois dê enter e finalize com control x para salvar:

sudo nano /etc/nginx/sites-available/default
```
