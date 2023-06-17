package com.example.springredispubsub.publisher;

import com.example.springredispubsub.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publisher {

    private RedisTemplate template;
    private ChannelTopic topic;

    @Autowired
    public Publisher(RedisTemplate template, ChannelTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    @PostMapping("/publish")
    public String publish(@RequestBody Product product) {
        this.template.convertAndSend(this.topic.getTopic(), product.toString());
        return "Event published!!";
    }
}
