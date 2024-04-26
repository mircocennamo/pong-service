#!/bin/sh
echo starting: Ping-Service.
export OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4318
#export OTEL_EXPORTER_OTLP_HEADERS="Authorization=Bearer xxxxxxxxxxx"
export OTEL_METRICS_EXPORTER=otlp
export OTEL_LOGS_EXPORTER=otlp
export OTEL_RESOURCE_ATTRIBUTES=service.name=ping-collector-service,service.version=1.0.0,deployment.environment=pre-production


java -XX:+UseZGC -XX:+ZGenerational -Xms1g -Xmx1g -XX:+ExitOnOutOfMemoryError -javaagent:./opentelemetry-javaagent.jar  -jar ./ping-service-1.0.0-SNAPSHOT.jar  2>&1 
echo $! > ./pid.file
