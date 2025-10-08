# ead-microservices
This repository is related to the EAD Backend Microservices

# docker DB
docker run --name postgres-ead --restart=always -p 5432:5432 -e POSTGRES_DB=ead-authuser -e POSTGRES_PASSWORD=ead1234 -d postgres

