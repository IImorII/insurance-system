package by.atsitou.policy;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import by.atsitou.policy.service.api.v1.Health;

@Client(id = "/policy-service", path = "/hello")
public interface HelloTestClient {

    @Get
    HttpStatus index();

    @Get("/version")
    Health version();
}