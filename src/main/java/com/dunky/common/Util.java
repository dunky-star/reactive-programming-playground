package com.dunky.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);
    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker(){
        return faker;
    }

    public static void sleepSeconds(int seconds) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(seconds));
    }
}
