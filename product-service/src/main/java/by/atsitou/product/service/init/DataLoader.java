package by.atsitou.product.service.init;

import by.atsitou.product.service.domain.Product;
import by.atsitou.product.service.infrastructure.adapters.db.ProductsRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final ProductsRepository productsRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent serverStartupEvent) {
        List<Product> allProducts = productsRepository.findAll().blockingGet();

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("CAR"))) {
            productsRepository.add(DemoProductsFactory.car()).blockingGet();
        }

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("FAI"))) {
            productsRepository.add(DemoProductsFactory.farm()).blockingGet();
        }

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("HSI"))) {
            productsRepository.add(DemoProductsFactory.house()).blockingGet();
        }

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("TRI"))) {
            productsRepository.add(DemoProductsFactory.travel()).blockingGet();
        }
    }
}
