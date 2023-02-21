package com.georgyi.reactdemo.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class FluxAndMonoServices {
    public Flux<String> fruitsFlux(){
       return Flux.fromIterable(List.of("Mongo","Orange","Banana")).log();
    }

    public Flux<String> fruitsFluxMap(){
        return Flux.fromIterable(List.of("Mongo","Orange","Banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() > number);
    }

    public Flux<String> fruitsFluxFlatMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int number){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .filter(s -> s.length() > number)
                .map(String::toUpperCase);
    }

    public Flux<String> fruitsFluxConcatMap(){
        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    public Mono<String> fruitMono(){
        return Mono.just("Mongo");
    }

    public Mono<List<String>> fruitMonoFlatMap(){
        return Mono.just("Mongo")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitMonoFlatMapMany(){
        return Mono.just("Mongo")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number){

        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Mango","Orange","Banana"))
                .transform(filterData)
                .log();
                //.filter(s -> s.length() > number);
    }

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices =
                new FluxAndMonoServices();

        fluxAndMonoServices.fruitsFlux()
                .subscribe(s -> {
                    System.out.println("s = " + s);
                });

        fluxAndMonoServices.fruitMono()
                .subscribe(s ->{
                    System.out.println("Mono -> s = " + s);
                });

    }

}
