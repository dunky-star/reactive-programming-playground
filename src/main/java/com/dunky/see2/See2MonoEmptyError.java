package com.dunky.see2;

import com.dunky.common.Util;
import reactor.core.publisher.Mono;

public class See2MonoEmptyError {

    public static void main(String[] args) {
        getUsername(1)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getUsername(int userId) {
      return switch (userId) {
          case 1 -> Mono.just("dunky");
          case 2 -> Mono.just("Lamaro");
          case 3 -> Mono.empty(); // No username for userId 3
          default -> Mono.error(new IllegalArgumentException("Invalid userId: " + userId));
      };
    }
}
