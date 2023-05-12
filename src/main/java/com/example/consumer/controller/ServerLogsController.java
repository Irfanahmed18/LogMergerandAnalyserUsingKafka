package com.example.consumer.controller;

import com.example.consumer.model.ServerLogs;
import com.example.consumer.repository.ServerLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63343")
public class ServerLogsController {
    @Autowired
    ServerLogsRepository serverLogsRepository;

    @GetMapping("/serverlogs")
    public ResponseEntity<List<ServerLogs>> getAllServerLogs(){
        List<ServerLogs> serverLogs = serverLogsRepository.findAll();
        return new ResponseEntity<>(serverLogs, HttpStatus.OK);
    }

    @GetMapping("/serverlogs/{loglevel}")
    public ResponseEntity<List<ServerLogs>> getServerLogsByLogLevel(@PathVariable String loglevel){
        List<ServerLogs> serverLogs = serverLogsRepository.findByLoglevel(loglevel);
        return new ResponseEntity<>(serverLogs, HttpStatus.OK);
    }

    @GetMapping("/serverlogs/{servicename}")
    public ResponseEntity<List<ServerLogs>> getServerLogsByApplicationName(@PathVariable String servicename){
        List<ServerLogs> serverLogs = serverLogsRepository.findByApplicationName(servicename);
        return new ResponseEntity<>(serverLogs, HttpStatus.OK);
    }

}
