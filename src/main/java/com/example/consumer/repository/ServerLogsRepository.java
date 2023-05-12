package com.example.consumer.repository;

import com.example.consumer.model.ServerLogs;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServerLogsRepository extends JpaRepository<ServerLogs, Long> {
    List<ServerLogs> findByLoglevel(String loglevel);
    List<ServerLogs> findByApplicationName(String applicationName);
}
