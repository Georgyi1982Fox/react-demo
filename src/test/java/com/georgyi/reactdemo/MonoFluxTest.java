package com.georgyi.reactdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public class MonoFluxTest{

    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("Georgyi")
                .then(Mono.error(new RuntimeException("Exception accured")))
                .log();
        monoString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }



}
