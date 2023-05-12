package com.example.consumer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "serverlogs")
public class ServerLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "thread")
    private String thread;

    @Column(name = "loglevel")
    private String loglevel;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "applicationName")
    private String applicationName;

    @Column(name = "logger")
    private String logger;

    @Column(name = "message")
    private String message;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServerLogs() {
    }

    public ServerLogs(String thread, String loglevel, String timestamp, String applicationName, String logger, String message) {
        this.thread = thread;
        this.loglevel = loglevel;
        this.timestamp = timestamp;
        this.applicationName = applicationName;
        this.logger = logger;
        this.message = message;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ServerLogs{" +
                "id=" + id +
                ", thread='" + thread + '\'' +
                ", loglevel='" + loglevel + '\'' +
                '}';
    }
}
