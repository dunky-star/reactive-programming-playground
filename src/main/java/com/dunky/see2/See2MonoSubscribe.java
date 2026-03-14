package com.dunky.see2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class See2MonoSubscribe {
    private static final Logger log = LoggerFactory.getLogger(See2MonoSubscribe.class);
    public static void main(String[] args) {
        var mono = Mono.just(1);
        mono.subscribe(
                data -> log.info("Received: " + data), // onNext
                error -> log.error("Error: " + error.getMessage()), // onError
                () -> log.info("Completed!"), // onComplete
                subscription -> subscription.request(1)
        );

    }
}
