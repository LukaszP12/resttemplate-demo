package org.example.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
@EnableWebMvc
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class ResttemplateApplication {

    @Autowired
    ShawnMendesProxy shawnMendesClient;

    public static void main(String[] args) {
        SpringApplication.run(ResttemplateApplication.class);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToShawnMendesEndpoint() throws JsonProcessingException {
        ShawnMendesResponse response = shawnMendesClient.makeShawnMendesRequest("shawnmendes", 5);
        System.out.println(response);
        Integer integer = response.resultCount();
        List<ShawnMendesResult> results = response.results();
    }
}
