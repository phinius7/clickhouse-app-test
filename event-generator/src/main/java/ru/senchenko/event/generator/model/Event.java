package ru.senchenko.event.generator.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Date;

public class Event {
    private final String key;
    private final String value;
    private final boolean isSuccess;
    private final Date timestamp;

    public Event(String key, String value, boolean isSuccess, Date timestamp) {
        this.key = key;
        this.value = value;
        this.isSuccess = isSuccess;
        this.timestamp = timestamp;
    }

    @JsonGetter("key")
    public String getKey() {
        return key;
    }

    @JsonGetter("value")
    public String getValue() {
        return value;
    }

    @JsonGetter("isSuccess")
    public boolean isSuccess() {
        return isSuccess;
    }

    @JsonGetter("timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", isSuccess=" + isSuccess +
                ", timestamp=" + timestamp +
                '}';
    }
}
