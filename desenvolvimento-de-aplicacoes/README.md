# Trabalho - Aplicações WEB

# Doação Sangue Web

Projeto composto por front-end em Angular 20 e back-end em Spring Boot 3.

## 🧰 Pré‑requisitos

* Docker (versão recente)
* Docker Compose ou plugin `docker compose`
* (Opcional) IDE/editor para subir separadamente

## 🚀 Como rodar localmente

```bash
# Clone o repositório
git clone https://github.com/gabivianasa/faculdade.git
cd desenvolvimento-de-aplicacoes

# Suba os serviços
docker compose up --build

# Acesse:
# Front-end: http://localhost/
# Back-end:  http://localhost:8080/
# H2 Console: http://localhost:8080/h2-console   (usuário, senha conforme application.yml)
```

## 🛑 Parar os serviços

```bash
docker compose down
```

Se quiser limpar imagens ou volumes:

```bash
docker compose down --rmi all --volumes
```

<p align="center">Gabi 🚀</p>
