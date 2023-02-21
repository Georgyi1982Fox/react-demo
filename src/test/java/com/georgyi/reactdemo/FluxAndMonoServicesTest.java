package com.georgyi.reactdemo;

import com.georgyi.reactdemo.service.FluxAndMonoServices;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
public class FluxAndMonoServicesTest {
    FluxAndMonoServices fluxAndMonoServices =
            new FluxAndMonoServices();
    @Test
    void fruitsFlux(){
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();

        StepVerifier.create(fruitsFlux)
                .expectNext("Mongo", "Orange", "Banana")
                .verifyComplete();
    }
    @Test
    void fruitMono(){
        var fruitsMono = fluxAndMonoServices.fruitMono();
        StepVerifier.create(fruitsMono)
                .expectNext("Mongo")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap(){
        var fruitsFluxMap = fluxAndMonoServices.fruitsFluxMap();

        StepVerifier.create(fruitsFluxMap)
                .expectNext("MONGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    public void fruitsFluxFilterTest(){
     var fruitsFluxFilter = fluxAndMonoServices.fruitsFluxFilter(5).log();

     StepVerifier.create(fruitsFluxFilter)
             .expectNext("Orange", "Banana")
             .verifyComplete();
    }

    @Test
    public void fruitsFluxFilterMapTest(){
        var fruitsMapFilter = fluxAndMonoServices.fruitsFluxFilterMap(5);

        StepVerifier.create(fruitsMapFilter)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapTest() {
        var fruitsMapFilter = fluxAndMonoServices.fruitsFluxFlatMap();

        StepVerifier.create(fruitsMapFilter)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsyncTest() {
        var fruitsMapFilter = fluxAndMonoServices.fruitsFluxFlatMapAsync();

        StepVerifier.create(fruitsMapFilter)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitMonoFlatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void testFluxConcatMap(){
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatMap();

        StepVerifier.create(fruitsFlux)
                .expectNextCount(17)
                .verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany(){
        var fruitFlux = fluxAndMonoServices.fruitMonoFlatMapMany();

        StepVerifier.create(fruitFlux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform(){
        var fruitFlux = fluxAndMonoServices.fruitsFluxTransform(5);

        StepVerifier.create(fruitFlux)
                .expectNext("Orange","Banana")
                .verifyComplete();

    }


}
