package com.example.consumer.kafkaservice;

import com.example.consumer.model.ServerLogs;
import com.example.consumer.repository.ServerLogsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class KafkaLogConsumer {

    @Autowired
    ServerLogsRepository serverLogsRepository;

    Logger LOG = LoggerFactory.getLogger(KafkaLogConsumer.class);

    @KafkaListener(topics = "quickstart")
    void listner(String data) {
        LOG.info(data);
        String regex = "(\\d{2}:\\d{2}:\\d{2}\\.\\d{3}) (\\S+) \\[(\\S+)\\] (\\S+)  (\\S+) - (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            ServerLogs serverLogs = new ServerLogs();
            serverLogs.setTimestamp(matcher.group(1));
            serverLogs.setApplicationName(matcher.group(2));
            serverLogs.setThread(matcher.group(3));
            serverLogs.setLoglevel(matcher.group(4));
            serverLogs.setLogger(matcher.group(5));
            String message = matcher.group(6);
            if (message.length() > 256) {
                message = message.substring(0,255);
            }
            serverLogs.setMessage(message);
            serverLogsRepository.save(serverLogs);
        } else {
            System.out.println("Log doesn't match the pattern: " + data);
        }
    }

}
