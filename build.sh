#!/bin/bash

echo "Building and pushing all services..."

mvn -pl user-service jib:build
mvn -pl booking-service jib:build
mvn -pl category-service jib:build
mvn -pl payment-service jib:build
mvn -pl salon-service jib:build
mvn -pl service-offering jib:build
mvn -pl notification-service jib:build
mvn -pl review-service jib:build
mvn -pl ai-service jib:build
mvn -pl gateway-server jib:build
mvn -pl eureka-server jib:build

echo "Done!"