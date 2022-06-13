package by.atsitou.pricing.service.api.v1.commands.calculateprice;

import by.atsitou.command.bus.api.Command;
import by.atsitou.pricing.service.api.v1.commands.calculateprice.dto.QuestionAnswer;

import java.time.LocalDate;
import java.util.List;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculatePriceCommand implements Command<CalculatePriceResult> {
    private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private List<String> selectedCovers;
    private List<QuestionAnswer> answers;
}
