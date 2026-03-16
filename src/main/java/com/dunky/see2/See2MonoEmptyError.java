package com.dunky.see2;

import com.dunky.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class See2MonoEmptyError {

    private static final Logger log = LoggerFactory.getLogger(See2MonoEmptyError.class);

    public static void main(String[] args) throws InterruptedException {
        getUsername(1)
                .subscribe(Util.subscriber());

        var list = List.of(10, 215, 3, 455, 555, 105, 206, 100, 1000);
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

        Mono.fromFuture(() -> getNameAsync())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }

    private static Mono<String> getUsername(int userId) {
      return switch (userId) {
          case 1 -> Mono.just("dunky");
          case 2 -> Mono.just("Lamaro");
          case 3 -> Mono.empty(); // No username for userId 3
          default -> Mono.error(new IllegalArgumentException("Invalid userId: " + userId));
      };
    }

    private static int sum(List<Integer> list) {
        log.info("Finding the sum: {}", list);
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    private static CompletableFuture<String> getNameAsync() {
        return CompletableFuture.supplyAsync(() -> {
           log.info("Finding the username");
           return Util.faker().name().username();
        });
    }
}
