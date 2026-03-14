package com.dunky.see1.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled = false;
    private final Faker faker;
    private static final int MAX_DATA = 100; // Maximum number of items to publish
    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;

        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if (isCancelled) {
            log.warn("Request ignored. Subscription is cancelled.");
            return;
        }
        log.info("Requesting {} items.", requested);
        // Here you would typically fetch data and call subscriber.onNext() for each item,
        // and then call subscriber.onComplete() when done.
        for (int i = 0; i < requested && count < MAX_DATA; i++) {
            String email = faker.internet().emailAddress();
            subscriber.onNext(email);
            count++;
        }
        if (count >= MAX_DATA) {
            log.info("Published all items. Completing subscription.");
            subscriber.onComplete();
            this.isCancelled = true; // Mark as cancelled to prevent further requests
        }

    }

    @Override
    public void cancel() {
        log.info("Subscription cancelled.");
        this.isCancelled = true;
    }
}
