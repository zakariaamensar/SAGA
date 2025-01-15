package com.microservice.orchestrator.Steps;

import reactor.core.publisher.Mono;

public interface Step<RequestType, ResponseType> {
    public Mono<ResponseType> process(RequestType request);

    public Mono<ResponseType> rollback(RequestType request);
}
