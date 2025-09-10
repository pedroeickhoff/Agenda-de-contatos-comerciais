```bash
# 1. Atualizar pacotes
sudo apt update && sudo apt upgrade -y

# 2. Instalar o MySQL
sudo apt install mysql-server -y

# 3. Editar configuração para aceitar conexões externas
sudo sed -i 's/^bind-address.*/bind-address = 0.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf
sudo sed -i 's/^mysqlx-bind-address.*/mysqlx-bind-address = 0.0.0.0/' /etc/mysql/mysql.conf.d/mysqld.cnf

# 4. Reiniciar serviço
sudo systemctl restart mysql

# 5. Entrar no MySQL como root
sudo mysql -u root -p

# 6. Criar o usuário

CREATE USER 'appuser'@'%' IDENTIFIED BY 'Senha123!';
GRANT ALL PRIVILEGES ON *.* TO 'appuser'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

# 7. Criar o banco de dados, utilizá-lo e depois criar a tabela
CREATE DATABASE minha_base;
USE minha_base;

CREATE TABLE Contatos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    cep CHAR(8),
    empresa VARCHAR(255),
    rua VARCHAR(20),
    bairro VARCHAR(20),
    numero VARCHAR(20),
    telefone VARCHAR(20),
    email VARCHAR(50),
    cargo VARCHAR(20)
);
```
