#!/bin/sh
echo "create database data" | mysql -u root -p

cp application.properties UserManagement/src/main/resources/
cp application.properties AuctionManagement/src/main/resources/

cd UserManagement
docker build --tag user-service:latest -f Dockerfile .


cd ../AuctionManagement
docker build --tag auction-service:latest -f Dockerfile .

docker run -d --network host user-service:latest
docker run -d --network host auction-service:latest

