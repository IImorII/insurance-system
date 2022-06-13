package by.atsitou.product.service.infrastructure.adapters.web;

import io.micronaut.http.annotation.Controller;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import by.atsitou.product.service.api.v1.ProductDto;
import by.atsitou.product.service.api.v1.ProductOperations;
import by.atsitou.product.service.domain.Products;

import java.util.List;

@Controller("/products")
@RequiredArgsConstructor
public class ProductsController implements ProductOperations {

    private final Products products;

    @Override
    public Single<List<ProductDto>> getAll() {
        return products.findAll().map(ProductsAssembler::map);
    }

    @Override
    public Maybe<ProductDto> get(String productCode) {
        return products.findOne(productCode).map(ProductsAssembler::map);
    }
}
