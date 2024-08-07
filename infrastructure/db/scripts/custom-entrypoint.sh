#!/bin/bash
set -e

echo "Debug: Starting custom entrypoint script execution..."

# Print Environment Variables for Debugging
echo "KAFKA_BROKER_ID: $KAFKA_BROKER_ID"
echo "KAFKA_ZOOKEEPER_CONNECT: $KAFKA_ZOOKEEPER_CONNECT"
echo "KAFKA_ADVERTISED_LISTENERS: $KAFKA_ADVERTISED_LISTENERS"
echo "KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: $KAFKA_LISTENER_SECURITY_PROTOCOL_MAP"
echo "KAFKA_INTER_BROKER_LISTENER_NAME: $KAFKA_INTER_BROKER_LISTENER_NAME"
echo "KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: $KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR"
echo "KAFKA_AUTO_CREATE_TOPICS_ENABLE: $KAFKA_AUTO_CREATE_TOPICS_ENABLE"

# Print volume contents for debugging
echo "Volume contents:"
ls -la /scripts

# Start Kafka
echo "Starting Kafka..."
/etc/confluent/docker/run &

echo "Debug: Kafka run command initiated."

# Wait for Kafka to start
echo "Waiting for Kafka to start..."
sleep 20

# Create Kafka topic
echo "Creating Kafka topic 'notification-topic'..."
kafka-topics --create --topic notification-topic --partitions 10 --replication-factor 1 --if-not-exists --bootstrap-server localhost:9092

echo "Debug: Kafka topic creation command issued."

# Keep the container running
wait
echo "Debug: Entrypoint script execution completed."
