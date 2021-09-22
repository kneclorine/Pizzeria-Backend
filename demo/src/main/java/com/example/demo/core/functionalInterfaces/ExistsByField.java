package com.example.demo.core.functionalInterfaces;

import reactor.core.publisher.Mono;

public interface ExistsByField {
    Mono<Boolean> exists(String field);
}
