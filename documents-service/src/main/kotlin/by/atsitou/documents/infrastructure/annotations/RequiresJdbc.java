package by.atsitou.documents.infrastructure.annotations;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Requires(property = "datasources.default.url", notEnv = Environment.TEST)
public @interface RequiresJdbc {
}
