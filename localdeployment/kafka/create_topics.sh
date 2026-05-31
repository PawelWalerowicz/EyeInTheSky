#!/bin/bash
set -e

KAFKA_BIN=/opt/kafka/bin

echo "Waiting for Kafka broker..."

until $KAFKA_BIN/kafka-broker-api-versions.sh --bootstrap-server kafka:9092 >/dev/null 2>&1; do
  sleep 1
done

echo "Kafka is ready"

$KAFKA_BIN/kafka-topics.sh --bootstrap-server kafka:9092 \
  --create --if-not-exists \
  --topic current-flights-data \
  --partitions 3 \
  --replication-factor 1

echo "Done"