package by.atsitou.policy.infrastructure.adapters.web;

import by.atsitou.policy.service.api.v1.Health;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {

    @Get
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Get("/version")
    public Health version() {
        return new Health("1.0", "OK");
    }
}
