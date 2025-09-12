# Projeto com 3 VMs (Proxy, Aplicação e Banco de Dados)

Para o funcionamento do projeto é necessário configurar 3 máquinas virtuais (VMs):
- VM1: Proxy reverso
- VM2: Back-end e Front-end
- VM3: Banco de dados

As 3 VMs devem estar configuradas em rede interna utilizando o modelo Custom com Host-Only. Além disso, a VM1 (proxy reverso) deve ter também a rede em modo NAT para permitir acesso externo.

## Configuração do Banco de Dados (VM3)
Na VM3, descubra o IP executando:
ip a

Depois edite o arquivo application.properties do projeto e substitua o IP obtido:
spring.datasource.url=jdbc:mysql://IPdaSuaVM:3306/projeto?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

## Configuração do Proxy Reverso (VM1)
Na VM2, descubra o IP executando:
ip a

Substitua o valor nas configurações do proxy reverso na VM1. Dessa forma, ao acessar o IP da VM1 (NAT), você será redirecionado para a aplicação que roda na VM2.

Exemplo de configuração do Nginx (/etc/nginx/sites-available/default):
server {
    listen 80;
    server_name _;
    location / {
        proxy_pass http://IPdaVM2:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}

Após editar, reinicie o nginx:
sudo systemctl restart nginx

## Ajuste dos Arquivos Front-end (VM2)
Nos arquivos HTML da aplicação, substitua o IP no trecho:
const apiUrl = "http://IPdaSuaVM1NAT/(resto do endereço)";

Arquivos que precisam ser editados:
- cadastro.html
- contatos.html
- editar.html

## Exemplo de application.properties (VM2)
spring.application.name=suadatabase

spring.datasource.url=jdbc:mysql://IPdaVM3/projeto?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=SeuUsuário
spring.datasource.password=SuaSenha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

## Resumo do Fluxo
- O usuário acessa via IP da VM1 (NAT).
- O proxy reverso redireciona a requisição para a VM2.
- A VM2 se conecta ao banco de dados na VM3.

## Rode o código
- utilize o comando ./mvnw spring-boot:run dentro da pasta do projeto da VM2 para processar a aplicação
