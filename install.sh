#!/bin/bash
echo '******Building the entire project******'
mvn11 clean package -DskipTests

echo '******Building docker files******'
docker build -t car .

echo '******Going to run network via docker-compose******'
docker-compose up
