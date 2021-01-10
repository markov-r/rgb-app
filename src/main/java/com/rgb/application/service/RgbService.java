package com.rgb.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgb.application.model.Rgb;
import com.rgb.application.repository.RgbRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RgbService {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private RgbRepository repository;

    public Rgb parseJson(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, Rgb.class);
        } catch (JsonProcessingException e) {
            log.error("Can't parse json from string {}", json);
            throw new RuntimeException("Can't parse json from string " + json);
        }
    }

    public Rgb save(Rgb rgb) {
        return repository.save(rgb);
    }

    public String createJson(Rgb rgb) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(rgb);
        } catch (JsonProcessingException e) {
            log.error("Can't create json file from RGB {}", rgb.toString());
            throw new RuntimeException("Can't create json file from RGB " + rgb.toString());
        }
    }
}
