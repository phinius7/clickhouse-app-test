package ru.senchenko.event.generator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senchenko.event.generator.model.Event;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class GeneratorService {
    private static final Logger logger = LoggerFactory.getLogger(GeneratorService.class);
    private final Random random = new Random();

    private final EventProducerService eventProducerService;

    @Autowired
    public GeneratorService(EventProducerService eventProducerService) {
        this.eventProducerService = eventProducerService;
    }

    private Event generateEvent() {
        final String key = UUID.randomUUID().toString();
        final String value = String.valueOf(random.nextLong());
        final boolean isSuccess = random.nextBoolean();
        final Date timestamp = new Date();
        return new Event(key, value, isSuccess, timestamp);
    }

    public void sendMessage() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Event obj = generateEvent();
            eventProducerService.startTransaction(obj);
            Thread.sleep(100);
        }
    }

}
