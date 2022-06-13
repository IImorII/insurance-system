package by.atsitou.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Maybe;
import by.atsitou.product.service.api.v1.ProductDto;
import by.atsitou.product.service.api.v1.ProductOperations;

@Client(id = "product-service", path = "/products")
@Retryable(attempts = "2", delay = "2s")
public interface ProductGatewayClient extends ProductOperations {

    @Override
    Maybe<ProductDto> get(String productCode);
}
