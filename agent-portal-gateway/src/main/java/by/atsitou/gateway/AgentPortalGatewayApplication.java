package by.atsitou.gateway;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Gateway API",
                version = "1.0"
        )
)
public class AgentPortalGatewayApplication {
    public static void main(String[] args) {
        Micronaut.run(AgentPortalGatewayApplication.class);
    }
}