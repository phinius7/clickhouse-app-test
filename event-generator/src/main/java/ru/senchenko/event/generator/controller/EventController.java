package ru.senchenko.event.generator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senchenko.event.generator.service.GeneratorService;

@RestController
@RequestMapping("/start")
public class EventController {
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private final GeneratorService generatorService;

    @Autowired
    public EventController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @PostMapping
    public void startGeneration() {
        logger.info("Start process");
        try {
            generatorService.sendMessage();
        } catch (InterruptedException e) {
            logger.error("Can't sleep", e);
        }
    }
}
