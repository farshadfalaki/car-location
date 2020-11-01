package com.farshad.car.config;

import com.mongodb.client.MongoDatabase;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Configuration
public class AppConfiguration {

    @Bean
    public LockProvider lockProvider(MongoTemplate mongoTemplate) {
        CodecRegistry pojoCodecRegistry = fromRegistries(mongoTemplate.getDb().getCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase database = mongoTemplate.getDb().withCodecRegistry(pojoCodecRegistry);
        return new MongoLockProvider(database);
    }
}
