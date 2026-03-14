package com.dunky.see1.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class PublisherImpl implements Publisher<String> {
    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        // 1. Create a Subscription and pass it to the Subscriber
        SubscriptionImpl subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
