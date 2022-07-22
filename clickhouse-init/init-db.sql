CREATE DATABASE IF NOT EXISTS test_db;

CREATE TABLE IF NOT EXISTS test_db.events (
    key       String,
    value     String,
    isSuccess Bool,
    timestamp DateTime64
)   Engine = MergeTree
    PARTITION BY toYYYYMMDD(timestamp)
    ORDER BY (key, isSuccess, timestamp);

CREATE TABLE IF NOT EXISTS test_db.events_queue (
    key       String,
    value     String,
    isSuccess Bool,
    timestamp DateTime64
)   Engine = Kafka SETTINGS kafka_broker_list = 'kafka:29092',
                            kafka_topic_list = 'common-topic',
                            kafka_group_name = 'testCH',
                            kafka_format = 'JSONEachRow',
                            kafka_num_consumers = 2;

CREATE MATERIALIZED VIEW IF NOT EXISTS test_db.events_queue_mv TO test_db.events AS
SELECT key, value, isSuccess, timestamp
FROM test_db.events_queue;
