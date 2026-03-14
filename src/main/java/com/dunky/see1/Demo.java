package com.dunky.see1;

/*
1. publisher does not produce data unless the subscriber requests it.
2. publisher will produce only <= subscriber requested items. publisher can also produce 0 items!
3. subscriber can cancel the subscription. producer should stop at that moment as subscriber is no longer
    interested in receiving data.
4. producer can send the error signal to indicate something went wrong.
 */

import com.dunky.see1.publisher.PublisherImpl;
import com.dunky.see1.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo1();
    }

    private static void demo1() throws InterruptedException {
        // 1. Create a Publisher
        var publisher = new PublisherImpl();

        // 2. Create a Subscriber
        var subscriber = new SubscriberImpl();

        // 3. Subscribe the Subscriber to the Publisher
        publisher.subscribe(subscriber);

        // 4. Request data from the Publisher
        subscriber.getSubscription().request(10); // Request 10 items
        Thread.sleep(Duration.ofSeconds(2).toMillis()); // Simulate some delay
        subscriber.getSubscription().request(10); // Request 10 items
        Thread.sleep(Duration.ofSeconds(2).toMillis()); // Simulate some delay
        subscriber.getSubscription().request(10); // Request 10 items
        Thread.sleep(Duration.ofSeconds(2).toMillis()); // Simulate some delay
        subscriber.getSubscription().request(10); // Request 10 items
        Thread.sleep(Duration.ofSeconds(2).toMillis()); // Simulate some delay
        subscriber.getSubscription().request(10); // Request 10 items
        Thread.sleep(Duration.ofSeconds(2).toMillis()); // Simulate some delay
        // Optionally, you can cancel the subscription after some time
        // subscriber.getSubscription().cancel();
    }

}
